package com.inavi.global.maps.androiddemo.view.map

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.base.IgmBaseActivity
import com.inavi.global.mapsdk.maps.IgmMap
import com.inavi.global.mapsdk.maps.IgmMapFragment
import com.inavi.global.mapsdk.maps.IgmMapOptions

open class IgmMapFragmentActivity<T: ViewDataBinding>
constructor(
    @LayoutRes private val layoutResId: Int,
    private val options: IgmMapOptions?,
) : IgmBaseActivity<T>(layoutResId) {

    constructor() : this(
        layoutResId = R.layout.activity_igm_map_fragment,
        options = null,
    )

    constructor(layoutResId: Int) : this(
        layoutResId = layoutResId,
        options = null,
    )

    constructor(options: IgmMapOptions?) : this(
        layoutResId = R.layout.activity_igm_map_fragment,
        options = options,
    )

    override fun init(savedInstanceState: Bundle?) {
        initUI()
    }

    private fun initUI() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as? IgmMapFragment
            ?: IgmMapFragment.newInstance(options).also {
                supportFragmentManager.beginTransaction()
                    .add(R.id.map_fragment, it)
                    .commit()
            }

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(igmMap: IgmMap) {

    }
}
