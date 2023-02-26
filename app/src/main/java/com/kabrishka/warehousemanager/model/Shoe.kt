package com.kabrishka.warehousemanager.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe (val name: String,
                 val vendorCode: String,
                 val brand: String,
                 val size: String) : Parcelable {
    override fun toString(): String {
        return """
            Name: $name
            Vendor code: $vendorCode
            Brand: $brand
            Size: $size
        """.trimIndent()
    }
}