package ute.com.shopadvance

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(var title: String, var price: Int, var imageUrl: String,var id :String):Parcelable {
    constructor() : this("", 0,"","")

}