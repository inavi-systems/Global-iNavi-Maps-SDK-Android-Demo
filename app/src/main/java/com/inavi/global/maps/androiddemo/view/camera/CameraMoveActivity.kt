package com.inavi.global.maps.androiddemo.view.camera

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityCameraMoveBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.geometry.LatLng
import com.inavi.global.mapsdk.maps.CameraAnimationType
import com.inavi.global.mapsdk.maps.CameraUpdate
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.style.shapes.IgmMarker
import com.inavi.global.mapsdk.style.shapes.IgmMarkerIcons

class CameraMoveActivity : IgmMapFragmentActivity<ActivityCameraMoveBinding>(R.layout.activity_camera_move),
    AdapterView.OnItemSelectedListener {

    private var isInitPosition = true
    private var animationType = CameraAnimationType.Fly

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        initUI()
    }

    override fun onMapReady(igmMap: IgmMap) {
        IgmMarker().apply {
            position = POSITION1
            iconImage = IgmMarkerIcons.RED
            map = igmMap
        }

        IgmMarker().apply {
            position = POSITION2
            iconImage = IgmMarkerIcons.BLUE
            map = igmMap
        }

        binding.startCameraMove.setOnClickListener {
            onClickStartCameraMove(igmMap)
        }
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        animationType = when (position) {
            1 -> CameraAnimationType.Easing
            2 -> CameraAnimationType.Fly
            else -> CameraAnimationType.None
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun initUI() {
        val options = resources.getStringArray(R.array.igm_option_array_animation_type)

        with(binding.cameraAnimationType) {
            adapter = ArrayAdapter(this@CameraMoveActivity, android.R.layout.simple_list_item_1, options)
            onItemSelectedListener = this@CameraMoveActivity
            setSelection(CameraAnimationType.Fly.ordinal)
        }
    }

    private fun onClickStartCameraMove(inaviMap: IgmMap) {
        val newCameraPosition = CameraUpdate.targetTo(
            when (isInitPosition) {
                true -> POSITION1
                else -> POSITION2
            }
        ).setAnimationType(animationType, 3000)

        inaviMap.moveCamera(newCameraPosition)
        isInitPosition = !isInitPosition
    }

    companion object {
        private val POSITION1 = LatLng(24.157552, 120.66603)
        private val POSITION2 = LatLng(25.034146, 121.56452)
    }
}