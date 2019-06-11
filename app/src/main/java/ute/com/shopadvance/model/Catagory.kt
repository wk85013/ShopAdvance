package ute.com.shopadvance.model

data class Catagory(var id: String, var name: String) {
    override fun toString(): String {
        return name
    }


}