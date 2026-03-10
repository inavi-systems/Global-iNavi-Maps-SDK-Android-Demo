package com.inavi.global.maps.androiddemo.view.option

import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityMapOptionControllerBinding
import com.inavi.global.maps.androiddemo.utils.extension.setCheckListener
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.maps.IgmMapOptions

class MapControllerActivity : IgmMapFragmentActivity<ActivityMapOptionControllerBinding>(
    layoutResId = R.layout.activity_map_option_controller,
    options = IgmMapOptions()
        .zoomControlVisible(true)
        .locationButtonVisible(true),
) {

    override fun onMapReady(igmMap: IgmMap) {
        binding.chkControllerVisibleCompass.setCheckListener { isChecked ->
            igmMap.getUiSettings().isCompassVisible = isChecked
        }

        binding.chkControllerVisibleScaleBar.setCheckListener { isChecked ->
            igmMap.getUiSettings().isScaleBarVisible = isChecked
        }

        binding.chkControllerVisibleZoom.setCheckListener { isChecked ->
            igmMap.getUiSettings().isZoomControlVisible = isChecked
        }

        binding.chkControllerVisibleCurLocation.setCheckListener { isChecked ->
            igmMap.getUiSettings().isLocationButtonVisible = isChecked
        }
    }
}
