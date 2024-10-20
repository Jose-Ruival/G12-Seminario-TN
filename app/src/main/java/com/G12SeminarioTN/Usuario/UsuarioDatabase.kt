package com.G12SeminarioTN.Usuario

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usuario::class], version = 2)

abstract class UsuarioDatabase: RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao

    companion object{
        private var INSTANCIA: UsuarioDatabase? = null
        fun getDatabase(context: Context): UsuarioDatabase {
            if(INSTANCIA == null){
                synchronized(this){
                    INSTANCIA = Room.databaseBuilder(
                        context,
                        UsuarioDatabase::class.java, "usuario_database")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCIA!!
        }
    }

}