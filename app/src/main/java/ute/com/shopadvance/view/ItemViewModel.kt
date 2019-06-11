package ute.com.shopadvance.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ute.com.shopadvance.data.ItemRepositorty
import ute.com.shopadvance.model.Item

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    //    private var items = MutableLiveData<List<Item>>()//liveData
//    private var firestoreQueryLiveData = FirestoreQueryLiveData()
    private lateinit var itemRepositorty: ItemRepositorty

    init {
        itemRepositorty = ItemRepositorty(application)
    }

    fun getItems(): LiveData<List<Item>> {//取得firebase資料
//        return firestoreQueryLiveData
        return itemRepositorty.getAllItems()
    }

    fun setCatagory(category: String) {
//        firestoreQueryLiveData.setCategory(category)
        itemRepositorty.setCatagory(category)
    }
}