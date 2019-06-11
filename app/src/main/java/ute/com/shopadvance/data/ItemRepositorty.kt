package ute.com.shopadvance.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import androidx.lifecycle.LiveData
import ute.com.shopadvance.model.Item
import ute.com.shopadvance.view.FirestoreQueryLiveData

class ItemRepositorty(application: Application) {
    private var itemDao: ItemDao
    private lateinit var items: LiveData<List<Item>>
    private var firestoreQueryLiveData = FirestoreQueryLiveData()
    private var network = false

    init {
        itemDao = ItemDatabase.getDatabase(application).getItemDao()
        items = itemDao.getItems()
        val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        network = networkInfo.isConnected

    }

    fun getAllItems(): LiveData<List<Item>> {
        if (network) {
            items = firestoreQueryLiveData
        } else
            items = itemDao.getItems()

//        items = firestoreQueryLiveData

        return items


    }

    fun setCatagory(category: String) {
        if (network) {
            firestoreQueryLiveData.setCategory(category)
        } else
            items = itemDao.getItemsByCategory(category)
    }

}