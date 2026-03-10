package com.inavi.global.maps.androiddemo.view.option

import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityMapOptionGestureBinding
import com.inavi.global.maps.androiddemo.utils.extension.setCheckListener
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.maps.IgmMap

class MapGestureActivity : IgmMapFragmentActivity<ActivityMapOptionGestureBinding>(R.layout.activity_map_option_gesture) {

    override fun onMapReady(igmMap: IgmMap) {
        binding.chkGestureEnabledScroll.setCheckListener { isChecked ->
            igmMap.getUiSettings().isScrollGesturesEnabled = isChecked
        }

        binding.chkGestureEnabledZoom.setCheckListener { isChecked ->
            igmMap.getUiSettings().isZoomGesturesEnabled = isChecked
        }

        binding.chkGestureEnabledTilt.setCheckListener { isChecked ->
            igmMap.getUiSettings().isTiltGesturesEnabled = isChecked
        }

        binding.chkGestureEnabledRotate.setCheckListener { isChecked ->
            igmMap.getUiSettings().isRotateGesturesEnabled = isChecked
        }
    }
}
