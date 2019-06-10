package ute.com.shopadvance

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*

class FirestoreQueryLiveData : LiveData<QuerySnapshot>(), EventListener<QuerySnapshot> {
    lateinit var registration: ListenerRegistration
    var query = FirebaseFirestore.getInstance()
        .collection("items")
        .orderBy("viewCount", Query.Direction.DESCENDING)
        .limit(10)
    var isRegistered: Boolean = false

    override fun onActive() {
        registration = query.addSnapshotListener(this)
        isRegistered = true
    }


    override fun onInactive() {
        super.onInactive()
        if (isRegistered) {
            registration.remove()
        }
    }

    override fun onEvent(querySnapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) {
        if (querySnapshot != null && !querySnapshot.isEmpty) {
            value = querySnapshot
        }
    }

    fun setCategory(categoryId: String) {
        if (isRegistered) {
            registration.remove()
            isRegistered = false
        }
        if (categoryId.length > 0) {
            query = FirebaseFirestore.getInstance()
                .collection("items")
                .whereEqualTo("catagory", categoryId)
                .orderBy("viewCount", Query.Direction.DESCENDING)
                .limit(10)
        } else {
            query = FirebaseFirestore.getInstance()
                .collection("items")
                .orderBy("viewCount", Query.Direction.DESCENDING)
                .limit(10)

        }
        registration = query.addSnapshotListener(this)
        isRegistered = true
    }
}
