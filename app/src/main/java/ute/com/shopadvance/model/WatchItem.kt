package ute.com.shopadvance.model

import com.google.firebase.Timestamp

data class WatchItem(val id: String, val timestamp: Timestamp) {
    constructor(id: String) : this(id, Timestamp.now())
    constructor() : this("", Timestamp.now())


}