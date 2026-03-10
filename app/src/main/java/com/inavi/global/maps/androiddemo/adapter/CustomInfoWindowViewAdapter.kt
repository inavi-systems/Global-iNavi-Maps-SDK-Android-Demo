package com.inavi.global.maps.androiddemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.databinding.ViewCustomInfoWindowBinding
import com.inavi.global.mapsdk.style.shapes.IgmInfoWindow
import com.inavi.global.mapsdk.style.shapes.IgmInfoWindowViewAdapter

class CustomInfoWindowViewAdapter(
    private val context: Context,
) : IgmInfoWindowViewAdapter() {

    private val holder: ViewHolder by lazy {
        ViewHolder(
            binding = ViewCustomInfoWindowBinding.inflate(
                LayoutInflater.from(context),
                null,
                false
            )
        )
    }


    override fun getView(infoWindow: IgmInfoWindow): View {
        holder.bind(infoWindow)

        return holder.getView()
    }

    inner class ViewHolder(
        private val binding: ViewCustomInfoWindowBinding,
    ) {

        fun bind(infoWindow: IgmInfoWindow) {
            val marker = infoWindow.marker

            when (marker != null) {
                true -> {
                    binding.customInfoWindowIcon.setImageResource(R.drawable.ic_place_black_24dp)
                    binding.customInfoWindowText.text = marker.tag.toString()
                }
                else -> {
                    binding.customInfoWindowIcon.setImageResource(R.drawable.ic_my_location_black_24dp)
                    binding.customInfoWindowText.text = context.getString(
                        R.string.igm_format_custom_info_window_on_map,
                        infoWindow.position.latitude,
                        infoWindow.position.longitude,
                    )
                }
            }
        }

        fun getView(): View {
            return binding.root
        }
    }
}