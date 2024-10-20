package com.G12SeminarioTN

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun UserDao(): UserDao
    companion object{
        private var INSTANCIA: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase {
            if(INSTANCIA == null){
                synchronized(this){
                    INSTANCIA = Room.databaseBuilder(
                        context,
                        UserDatabase::class.java, "user_database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCIA!!
        }
    }
}
