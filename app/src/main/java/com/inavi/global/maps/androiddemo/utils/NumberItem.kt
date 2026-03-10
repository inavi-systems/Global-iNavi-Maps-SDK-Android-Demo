package com.inavi.global.maps.androiddemo.utils

import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.style.clustering.ClusterItem

data class NumberItem(
    private val position: LatLng,
    val number: Int,
) : ClusterItem {

    override fun getPosition() = position
}
