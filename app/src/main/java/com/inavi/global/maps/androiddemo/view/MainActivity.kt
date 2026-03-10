package com.inavi.global.maps.androiddemo.view

import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inavi.global.maps.androiddemo.R
import com.inavi.global.maps.androiddemo.base.BaseActivity
import com.inavi.global.maps.androiddemo.databinding.ActivityMainBinding
import com.inavi.global.maps.androiddemo.feature.FeatureAdapter
import com.inavi.global.maps.androiddemo.utils.Feature
import com.inavi.global.maps.androiddemo.utils.ItemClickSupport
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    ItemClickSupport.OnItemClickListener {

    private val features = mutableListOf<Feature?>()

    override fun init(savedInstanceState: Bundle?) {
        initUI()

        loadFeatures()

        ItemClickSupport.Companion.addTo(binding.mainActRc).setOnItemClickListener(this)
    }

    override fun onItemClicked(
        recyclerView: RecyclerView?,
        position: Int,
        view: View?
    ) {
        val feature = features.getOrNull(position) ?: return

        if (feature.name?.isEmpty() == true) {
            return
        }

        startFeature(feature)
    }

    private fun initUI() {
        with (binding.mainActRc) {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.addOnItemTouchListener(RecyclerView.SimpleOnItemTouchListener())
            this.setHasFixedSize(true)
        }
    }

    private fun loadFeatures() = CoroutineScope(Dispatchers.Default).launch {
        features.clear()

        val app: PackageInfo = runCatching {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES or PackageManager.GET_META_DATA)
        }.getOrElse {
            it.printStackTrace()
            return@launch
        }

        val packageName = applicationContext.packageName
        val metaDataKey = getString(R.string.igm_category)

        app.activities?.forEach { info ->
            if (info.labelRes != 0 && info.name.startsWith(packageName) && !info.name.equals(MainActivity::class.java.name)) {
                val label = getString(info.labelRes)
                val description = resolveString(info.descriptionRes)
                val category = resolveMetaData(info.metaData, metaDataKey)

                features.add(
                    Feature(
                        name = info.name,
                        label = label,
                        description = description,
                        category = category,
                    )
                )
            }
        }

        onFeaturesLoaded()
    }

    private fun resolveString(@StringRes stringRes: Int): String = runCatching {
        getString(stringRes)
    }.getOrDefault("-")

    private fun resolveMetaData(
        bundle: Bundle,
        key: String,
    ): String? {
        return bundle.getString(key)
    }

    private fun onFeaturesLoaded() {
        if (features.isEmpty()) {
            return
        }

        val newFeatures = features.toMutableList()
        val categorySize = features.mapNotNull { it?.category }.distinct().size

        var currentCategory = ""
        var addSize = 0

        for (i in 0 until (newFeatures.size + categorySize)) {
            val feature = features[i - addSize]
            val category = feature?.category ?: continue

            if (currentCategory != category) {
                addSize += 1
                newFeatures.add(i, Feature("", category, "", ""))
                currentCategory = category
            }
        }

        features.clear()
        features.addAll(newFeatures)

        binding.mainActRc.setAdapter(FeatureAdapter(features.filterNotNull().toList()))
    }

    private fun startFeature(feature: Feature) {
        val intent = Intent().apply {
            setComponent(ComponentName(packageName, feature.name ?: ""))
        }
        startActivity(intent)
    }
}