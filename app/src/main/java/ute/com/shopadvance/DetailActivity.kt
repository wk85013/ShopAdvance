package ute.com.shopadvance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_detail.*
import ute.com.shopadvance.model.Item

class DetailActivity : AppCompatActivity() {
    private val TAG: String? = DetailActivity::class.java.simpleName
    lateinit var item: Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        item = intent.getParcelableExtra<Item>("ITEM")
        Log.i(TAG, ": ${item.id}/${item.title}");
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl(item.content)//使用Firebase hosting功能上傳網頁
    }

    override fun onStart() {
        super.onStart()
        item.viewCount++
        item.id?.let {
            //檢查ID是否為Null,若不是Null則處理下面動作
            FirebaseFirestore.getInstance().collection("items")
                .document(it)
                .update("viewCount", item.viewCount)//更新指定欄位資料

        }

    }
}
