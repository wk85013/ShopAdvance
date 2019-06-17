package ute.com.shopadvance.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessageService : FirebaseMessagingService() {
    private val TAGG: String? = MyFirebaseMessageService::class.java.simpleName

    override fun onNewToken(token: String?) {
        Log.i(TAGG, "token: ${token}");
    }
}