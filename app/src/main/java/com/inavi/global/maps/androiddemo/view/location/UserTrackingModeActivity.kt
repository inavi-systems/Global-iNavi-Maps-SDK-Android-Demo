package com.inavi.global.maps.androiddemo.view.location

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ActivityUserTrackingModeBinding
import com.inavi.global.maps.androiddemo.view.map.IgmMapFragmentActivity
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.maps.IgmMapOptions
import com.inavi.global.mapsdk.maps.UserTrackingMode

class UserTrackingModeActivity : IgmMapFragmentActivity<ActivityUserTrackingModeBinding>(
    layoutResId = R.layout.activity_user_tracking_mode,
    options = IgmMapOptions().locationButtonVisible(true)
), AdapterView.OnItemSelectedListener {
    private var userTrackingMode = UserTrackingMode.None

    private var igmMap: IgmMap? = null

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)

        val options = resources.getStringArray(R.array.igm_option_array_location_tracking_mode)
        binding.spinnerLocation.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
        binding.spinnerLocation.onItemSelectedListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onMapReady(igmMap: IgmMap) {
        this.igmMap = igmMap
        igmMap.enableLocationComponent(this)
        igmMap.setUserTrackingMode(userTrackingMode)
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        when (position) {
            0 -> setUserTrackingMode(UserTrackingMode.None)
            1 -> setUserTrackingMode(UserTrackingMode.NoTracking)
            2 -> setUserTrackingMode(UserTrackingMode.Tracking)
            3 -> setUserTrackingMode(UserTrackingMode.TrackingCompass)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun setUserTrackingMode(mode: UserTrackingMode) {
        igmMap?.setUserTrackingMode(mode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        igmMap?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
