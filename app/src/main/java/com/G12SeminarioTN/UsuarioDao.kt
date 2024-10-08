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

    @Query("SELECT * FROM usuario_entity WHERE usuario = :nombreUsuario LIMIT 1")
    fun getUsuarioNombre(nombreUsuario: String): Usuario?

    @Query("SELECT * FROM usuario_entity WHERE usuario = :passwordUsuario LIMIT 1")
    fun getUsuarioPassword(passwordUsuario: String): Usuario?
}
