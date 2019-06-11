package ute.com.shopadvance.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ute.com.shopadvance.model.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun getItemDao(): ItemDao

    companion object {
        private lateinit var context: Context
        private val database: ItemDatabase by lazy {//執行時呼叫此方法by lazy就會執行
            Room.databaseBuilder(context, ItemDatabase::class.java, "mydb")
                .allowMainThreadQueries()
                .build()
        }
        fun getDatabase(context: Context): ItemDatabase {
            Companion.context = context
            return database
        }

    }

}