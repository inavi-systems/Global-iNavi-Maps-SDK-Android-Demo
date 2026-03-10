package com.inavi.global.maps.androiddemo.utils.extension

import android.content.res.Resources

fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
