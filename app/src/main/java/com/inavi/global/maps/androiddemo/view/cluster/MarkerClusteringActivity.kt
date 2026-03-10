package com.inavi.global.maps.androiddemo.view.cluster

import android.widget.Toast
import androidx.core.graphics.toColorInt
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityIgmMapFragmentBinding
import com.inavi.global.maps.androiddemo.utils.NumberItem
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.constants.IgmConstants
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.CameraAnimationType
import com.inavi.global.mapsdk.maps.CameraUpdate
import com.inavi.global.mapsdk.maps.IgmCameraPosition
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.maps.IgmMapOptions
import com.inavi.global.mapsdk.style.clustering.Cluster
import com.inavi.global.mapsdk.style.clustering.ClusterManager
import com.inavi.global.mapsdk.style.clustering.DefaultClusterIconGenerator
import com.inavi.global.mapsdk.style.shapes.IgmMarkerIcons
import com.inavi.global.mapsdk.style.shapes.IgmMarkerOptions

class MarkerClusteringActivity : IgmMapFragmentActivity<ActivityIgmMapFragmentBinding>(
    options = IgmMapOptions().camera(INIT_CAMERA)
        .zoomControlVisible(true),
), ClusterManager.OnRenderListener<NumberItem>,
    ClusterManager.OnClickListener<NumberItem> {

    private var igmMap: IgmMap? = null
    private var clusterManager: ClusterManager<NumberItem>? = null

    override fun onMapReady(igmMap: IgmMap) {
        this.igmMap = igmMap

        clusterManager = ClusterManager<NumberItem>(this, igmMap).also {
            it.setClusterIconGenerator(DefaultClusterIconGenerator.withColors(this, BACKGROUND_COLORS, CRITERIA))
            it.setOnRenderListener(this)
            it.setOnClickListener(this)
        }

        val items = arrayListOf<NumberItem>()
        for (i in 1..1000) {
            items.add(NumberItem(generateRandomPosition(), i))
        }

        clusterManager?.addItems(items)
    }

    override fun onRenderCluster(
        cluster: Cluster<NumberItem>,
        markerOptions: IgmMarkerOptions,
    ) {
        markerOptions.position = cluster.getPosition()
    }

    override fun onRenderClusterItem(
        item: NumberItem,
        markerOptions: IgmMarkerOptions,
    ) {
        markerOptions.position = item.getPosition();
        markerOptions.iconScale = 0.8f
        markerOptions.iconImage = MARKER_ICONS[item.number % MARKER_ICONS.size]
        markerOptions.title = "Marker #${item.number}"
    }

    override fun onClusterClick(
        cluster: Cluster<NumberItem>,
        markerOptions: IgmMarkerOptions,
    ): Boolean {
        val position = cluster.getPosition()
        val count = cluster.getCount()
        Toast.makeText(this,
            getString(R.string.igm_format_cluster_click, position.latitude, position.longitude, count),
            Toast.LENGTH_SHORT
        ).show()

        val cameraUpdate = CameraUpdate.targetTo(position)
        cameraUpdate.setAnimationType(CameraAnimationType.Easing)
        igmMap?.moveCamera(cameraUpdate)
        return true
    }

    override fun onClusterItemClick(clusterItem: NumberItem, markerOptions: IgmMarkerOptions): Boolean {
        val position = clusterItem.getPosition()
        Toast.makeText(this,
            getString(R.string.igm_format_cluster_item_click, position.latitude, position.longitude, clusterItem.number),
            Toast.LENGTH_SHORT
        ).show()

        val cameraUpdate = CameraUpdate.targetTo(position)
        cameraUpdate.setAnimationType(CameraAnimationType.Easing)
        igmMap?.moveCamera(cameraUpdate)
        return true
    }

    private fun randomScale(): Double = (0..Int.MAX_VALUE).random() / Int.MAX_VALUE.toDouble() * 2.0 - 1.0

    private fun generateRandomPosition(): LatLng {
        val extent = 0.02

        return LatLng(
            POSITION.latitude + extent * randomScale(),
            POSITION.longitude + extent * randomScale()
        )
    }

    companion object {
        private val INIT_CAMERA = IgmCameraPosition(IgmConstants.POSITION_DEFAULT.target, 13.0, 0.0, 0.0)
        private val POSITION = LatLng(INIT_CAMERA.target.latitude, INIT_CAMERA.target.longitude)
        private val BACKGROUND_COLORS = listOf(
            "#0099cc".toColorInt(),
            "#669900".toColorInt(),
            "#ff8800".toColorInt(),
            "#cc0000".toColorInt(),
            "#9933cc".toColorInt()
        )
        private val CRITERIA = listOf(10, 50, 100, 200, 500)
        private val MARKER_ICONS = mutableListOf(
            IgmMarkerIcons.RED,
            IgmMarkerIcons.GREEN,
            IgmMarkerIcons.BLUE,
            IgmMarkerIcons.YELLOW,
            IgmMarkerIcons.GRAY,
        )
    }
}