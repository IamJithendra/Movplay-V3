package com.jvktech.moviebuff.ui.screens.search

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.data.model.MediaType
import com.jvktech.moviebuff.ui.components.sections.PresentableGridSection
import com.jvktech.moviebuff.ui.components.sections.SearchGridSection
import com.jvktech.moviebuff.ui.screens.search.components.QueryTextField
import com.jvktech.moviebuff.ui.screens.search.components.SearchEmptyState
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.CaptureSpeechToText
import com.jvktech.moviebuff.utils.isNotEmpty

@Composable
fun SearchScreenContent(
    uiState: SearchScreenUIState,
    onQueryChanged: (query: String) -> Unit,
    onQueryCleared: () -> Unit,
    onResultClicked: (id: Int, type: MediaType) -> Unit,
    onMovieClicked: (Int) -> Unit,
    onQuerySuggestionSelected: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    val queryTextFieldFocusRequester = remember {
        FocusRequester()
    }
    val clearFocus = { focusManager.clearFocus(force = true) }
    val speechToTextLauncher = rememberLauncherForActivityResult(CaptureSpeechToText()) { result ->
        if (result != null) {
            focusManager.clearFocus()
            onQueryChanged(result)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        QueryTextField(
            query = uiState.query,
            focusRequester = queryTextFieldFocusRequester,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.extraSmall)
                .animateContentSize(),
            suggestions = uiState.suggestions,
            loading = uiState.queryLoading,
            showClearButton = uiState.searchState !is SearchState.EmptyQuery,
            voiceSearchAvailable = uiState.searchOptionsState.voiceSearchAvailable,
            info = {
                AnimatedVisibility(
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically(),
                    visible = uiState.searchState is SearchState.InsufficientQuery
                ) {
                    Text(text = stringResource(R.string.search_insufficient_query_length_info_text))
                }
            },
            onQueryChange = onQueryChanged,
            onQueryClear = {
                onQueryCleared()
                queryTextFieldFocusRequester.requestFocus()
            },
            onKeyboardSearchClicked = {
                clearFocus()
            },
            onVoiceSearchClick = {
                speechToTextLauncher.launch(null)
            }
        ) { suggestion ->
            clearFocus()
            onQuerySuggestionSelected(suggestion)
        }
        Crossfade(
            modifier = Modifier.fillMaxSize(),
            targetState = uiState.resultState,
            label = ""
        ) { state ->
            when (state) {
                is ResultState.Default -> {
                    val popular = state.popular.collectAsLazyPagingItems()

                    if (popular.isNotEmpty()) {
                        PresentableGridSection(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                top = MaterialTheme.spacing.medium,
                                start = MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.small,
                                bottom = MaterialTheme.spacing.large
                            ),
                            state = popular,
                            onPresentableClick = onMovieClicked
                        )
                    }
                }
                is ResultState.Search -> {
                    val result = state.result.collectAsLazyPagingItems()

                    if (result.isNotEmpty()) {
                        SearchGridSection(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                top = MaterialTheme.spacing.medium,
                                start = MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.small,
                                bottom = MaterialTheme.spacing.large
                            ),
                            state = result,
                            onSearchResultClick = { id, mediaType ->
                                clearFocus()
                                onResultClicked(id, mediaType)
                            }
                        )
                    } else {
                        SearchEmptyState(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = MaterialTheme.spacing.medium)
                                .padding(top = MaterialTheme.spacing.extraLarge),
                            onEditButtonClicked = {
                                queryTextFieldFocusRequester.requestFocus()
                            }
                        )
                    }

                }
            }
        }
    }
}