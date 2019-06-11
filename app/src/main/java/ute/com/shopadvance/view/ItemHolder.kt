package ute.com.shopadvance.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row.view.*
import ute.com.shopadvance.model.Item

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {

    var titleText = view.item_title
    var priceText = view.item_price
    var image = view.item_image
    var countText = view.item_count


    fun bintTo(item: Item) {
        titleText.setText(item.title)
        priceText.setText(item.price.toString())
        countText.setText(item.viewCount.toString())
        countText.setCompoundDrawablesWithIntrinsicBounds(android.R.drawable.ic_menu_view, 0, 0, 0)
        Glide.with(itemView.context)//使用Glide套件取得照片
            .load(item.imageUrl)
            .apply(RequestOptions().override(120))
            .into(image)
    }
}