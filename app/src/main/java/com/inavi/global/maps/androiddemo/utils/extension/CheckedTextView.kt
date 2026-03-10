package com.inavi.global.maps.androiddemo.utils.extension

import android.widget.CheckedTextView

fun CheckedTextView.setCheckListener(
    changedState: ((isChecked: Boolean) -> Unit)? = null,
) {
    changedState?.invoke(isChecked)
    setOnClickListener {
        isChecked = !isChecked
        changedState?.invoke(isChecked)
    }
}