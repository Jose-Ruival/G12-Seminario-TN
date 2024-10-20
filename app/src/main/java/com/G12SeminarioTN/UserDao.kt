package com.G12SeminarioTN

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user_entity")
    fun getAll(): List<User>

    @Query("SELECT * FROM user_entity WHERE usuario = :nombre LIMIT 1")
    fun getUsuarioNombre(nombre: String): User

    @Insert
    fun insert(usuarios: User)
}