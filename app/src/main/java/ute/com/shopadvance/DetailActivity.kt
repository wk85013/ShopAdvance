package ute.com.shopadvance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private val TAG: String? = DetailActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val item = intent.getParcelableExtra<Item>("ITEM")
        Log.i(TAG, ": ${item.id}/${item.title}");
        web_view.settings.javaScriptEnabled = true
        web_view.loadUrl("https://litotom.com/shop/android9/")
    }
}
