package ute.com.shopadvance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ItemViewModel : ViewModel() {
    private var items = MutableLiveData<List<Item>>()//liveData
    private var firestoreQueryLiveData = FirestoreQueryLiveData()

    fun getItems(): FirestoreQueryLiveData {//取得firebase資料
        return firestoreQueryLiveData
    }

    fun setCatagory(category:String){
        firestoreQueryLiveData.setCategory(category)

    }
}