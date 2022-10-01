package com.mahmoudashraf.core.androidextensions

import androidx.fragment.app.Fragment
import com.mahmoudashraf.core.R
import com.mahmoudashraf.core.exceptions.RickAndMortyException

fun Fragment.getMessageShouldDisplay(exception: RickAndMortyException): String {
    return when (exception) {
        is RickAndMortyException.NoConnection -> getString(R.string.lbl_no_connection_error_msg)
        is RickAndMortyException.Business -> exception.message ?:  getString(R.string.lbl_general_error_msg)
        else -> getString(R.string.lbl_general_error_msg)
    }
}
