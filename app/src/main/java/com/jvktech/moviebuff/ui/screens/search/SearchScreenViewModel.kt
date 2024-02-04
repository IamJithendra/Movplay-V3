package com.jvktech.moviebuff.ui.screens.search

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jvktech.moviebuff.BaseViewModel
import com.jvktech.moviebuff.data.model.DeviceLanguage
import com.jvktech.moviebuff.data.model.Presentable
import com.jvktech.moviebuff.data.model.SearchQuery
import com.jvktech.moviebuff.data.model.SearchResult
import com.jvktech.moviebuff.domain.usecase.*
import com.jvktech.moviebuff.domain.usecase.movie.GetPopularMoviesUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getDeviceLanguageUseCase: GetDeviceLanguageUseCaseImpl,
    private val getSpeechToTextAvailableUseCase: GetSpeechToTextAvailableUseCaseImpl,
    private val getCameraAvailableUseCase: GetCameraAvailableUseCaseImpl,
    private val mediaAddSearchQueryUseCase: MediaAddSearchQueryUseCaseImpl,
    private val mediaSearchQueriesUseCase: MediaSearchQueriesUseCaseImpl,
    private val getMediaMultiSearchUseCase: GetMediaMultiSearchUseCaseImpl,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCaseImpl
) : BaseViewModel() {
    private val deviceLanguage: Flow<DeviceLanguage> = getDeviceLanguageUseCase()
    private val queryDelay = 500.milliseconds
    private val minQueryLength = 3

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private val popularMovies: Flow<PagingData<Presentable>> =
        deviceLanguage.mapLatest { deviceLanguage ->
            getPopularMoviesUseCase(deviceLanguage)
        }.flattenMerge().cachedIn(viewModelScope)

    private val voiceSearchAvailable: Flow<Boolean> = getSpeechToTextAvailableUseCase()
    private val cameraSearchAvailable: Flow<Boolean> = getCameraAvailableUseCase()

    private val queryState: MutableStateFlow<QueryState> = MutableStateFlow(QueryState.default)
    private val suggestions: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    private val searchState: MutableStateFlow<SearchState> =
        MutableStateFlow(SearchState.EmptyQuery)
    private val resultState: MutableStateFlow<ResultState> =
        MutableStateFlow(ResultState.Default(popularMovies))
    private val queryLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val searchOptionState: StateFlow<SearchOptionsState> = combine(
        voiceSearchAvailable, cameraSearchAvailable
    ) { voiceSearchAvailable, cameraSearchAvailable ->
        SearchOptionsState(
            voiceSearchAvailable = voiceSearchAvailable,
            cameraSearchAvailable = cameraSearchAvailable
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, SearchOptionsState.default)

    private var queryJob: Job? = null

    val uiState: StateFlow<SearchScreenUIState> = combine(
        searchOptionState, queryState, suggestions, searchState, resultState
    ) { searchOptionsState, queryState, suggestions, searchState, resultState ->
        SearchScreenUIState(
            searchOptionsState = searchOptionsState,
            query = queryState.query,
            suggestions = suggestions,
            searchState = searchState,
            resultState = resultState,
            queryLoading = queryState.loading
        )
    }.stateIn(viewModelScope, SharingStarted.Eagerly, SearchScreenUIState.default)

    fun onQueryChange(queryText: String) {
        viewModelScope.launch {
            queryState.emit(queryState.value.copy(query = queryText))

            queryJob?.cancel()

            Timber.e("I am on query change")

            when {
                queryText.isBlank() -> {
                    searchState.emit(SearchState.EmptyQuery)
                    suggestions.emit(emptyList())
                    resultState.emit(ResultState.Default(popularMovies))
                }
                queryText.length < minQueryLength -> {
                    searchState.emit(SearchState.InsufficientQuery)
                    suggestions.emit(emptyList())
                }
                else -> {
                    val querySuggestions = mediaSearchQueriesUseCase(queryText)
                    suggestions.emit(querySuggestions)

                    Timber.e("I am on query change $querySuggestions")

                    queryJob = createQueryJob(queryText).apply {
                        start()
                    }

                    Timber.e("I am on query job $queryJob")
                }
            }
        }
    }

    fun onQueryClear() {
        onQueryChange("")
    }

    fun onQuerySuggestionSelected(searchQuery: String) {
        if (queryState.value.query != searchQuery) {
            onQueryChange(searchQuery)
        }
    }

    fun addQuerySuggestion(searchQuery: SearchQuery) {
        mediaAddSearchQueryUseCase(searchQuery)
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun createQueryJob(query: String): Job {
        return viewModelScope.launch(Dispatchers.IO) {
            try {
                delay(queryDelay)

                queryLoading.emit(true)

                val searchResults = deviceLanguage.mapLatest { deviceLanguage ->
                    getMediaMultiSearchUseCase(
                        query = query,
                        deviceLanguage = deviceLanguage
                    )
                }.flattenMerge().cachedIn(viewModelScope)

                searchState.emit(SearchState.ValidQuery)
                resultState.emit(ResultState.Search(searchResults))

                Timber.e("I am on result State $resultState")

            } catch (_: CancellationException) {

            } finally {
                withContext(NonCancellable) {
                    queryLoading.emit(false)
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        queryJob?.cancel()
    }
}

sealed class SearchState {
    object EmptyQuery : SearchState()
    object InsufficientQuery : SearchState()
    object ValidQuery : SearchState()
}

sealed class ResultState {
    data class Default(val popular: Flow<PagingData<Presentable>> = emptyFlow()) : ResultState()
    data class Search(val result: Flow<PagingData<SearchResult>>) : ResultState()
}

data class QueryState(
    val query: String?,
    val loading: Boolean
) {
    companion object {
        val default: QueryState = QueryState(
            query = null,
            loading = false
        )
    }
}