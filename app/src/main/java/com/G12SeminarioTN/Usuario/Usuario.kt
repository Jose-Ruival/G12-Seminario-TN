package com.G12SeminarioTN.Usuario

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario_entity")
public class Usuario(
    @ColumnInfo(name = "usuario") var usuario: String,
    @ColumnInfo(name = "contraseña") var contraseña: String,
    @ColumnInfo(name = "mail") var mail: String
) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}
