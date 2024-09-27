package com.G12SeminarioTN

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuario_entity")
    fun getAll(): List<Usuario>
    @Insert
    fun insert(usuario: Usuario)
}