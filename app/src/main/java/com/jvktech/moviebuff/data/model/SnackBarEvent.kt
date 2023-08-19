package com.jvktech.moviebuff.data.model

import androidx.annotation.StringRes
import com.jvktech.moviebuff.R

sealed class SnackBarEvent(@StringRes val messageStringRes: Int) {
    object NetworkDisconnected : SnackBarEvent(R.string.snack_bar_network_disconnected_label)
    object NetworkConnected : SnackBarEvent(R.string.snack_bar_network_connected_label)
}
