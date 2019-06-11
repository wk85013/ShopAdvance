package ute.com.shopadvance.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Parcelize
@IgnoreExtraProperties//
@Entity
data class Item(
    var title: String,
    var price: Int,
    var imageUrl: String,
    @PrimaryKey
    @get:Exclude var id: String,//避免該欄位被加入到document
    var content: String,
    var catagory: String,
    var viewCount: Int
) :
    Parcelable {
    constructor() : this("", 0, "", "", "", "", 0)

}