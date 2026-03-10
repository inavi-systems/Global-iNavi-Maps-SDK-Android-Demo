package com.inavi.global.maps.androiddemo.utils

import android.os.Parcel
import android.os.Parcelable

class Feature : Parcelable {
    val name: String?
    private val label: String?
    private val description: String?
    val category: String?

    constructor(
        name: String?,
        label: String?,
        description: String?,
        category: String?
    ) {
        this.name = name
        this.label = label
        this.description = description
        this.category = category
    }

    private constructor(parcel: Parcel) {
        name = parcel.readString()
        label = parcel.readString()
        description = parcel.readString()
        category = parcel.readString()
    }

    val isHeaderItem: Boolean
        get() = name == null || name.isEmpty()

    val simpleName: String?
        get() {
            val split: Array<String?> =
                name!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            return split[split.size - 1]
        }

    fun getLabel(): String? {
        return label ?: this.simpleName
    }

    fun getDescription(): String {
        return description ?: "-"
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeString(name)
        out.writeString(label)
        out.writeString(description)
        out.writeString(category)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Feature> = object : Parcelable.Creator<Feature> {
            override fun createFromParcel(parcel: Parcel): Feature {
                return Feature(parcel)
            }

            override fun newArray(size: Int): Array<Feature?> {
                return arrayOfNulls(size)
            }
        }
    }
}
