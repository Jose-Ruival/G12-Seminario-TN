package com.G12SeminarioTN

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    lateinit var etUsuario: EditText
    lateinit var etPassword: EditText
    lateinit var cbRecordarusuario: CheckBox
    lateinit var btnRegistrarse: Button
    lateinit var btnIniciarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        cbRecordarusuario = findViewById(R.id.cbRecordarUsuario)
        btnRegistrarse = findViewById(R.id.btnRegistrar)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        var passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario), "")

        if(usuarioGuardado!="" && passwordGuardado!= ""){
            if (usuarioGuardado != null) {
                startMainActivity(usuarioGuardado)
            }
        }




        btnRegistrarse.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnIniciarSesion.setOnClickListener {

            var usuario =  etUsuario.text.toString()
            var pass = etPassword.text.toString()
            if (usuario.isEmpty() || pass.isEmpty()){
                var mensaje= "Completar Datos"
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

            }else{

                val db = UsuarioDatabase.getDatabase(this)
                val usuarioDao = db.usuarioDao()

                val usuarioExiste = usuarioDao.getUsuarioNombre(usuario)



                if(usuarioExiste == null){
                    var mensaje2 = "El Usuario no Existe"
                    Toast.makeText(this, mensaje2, Toast.LENGTH_SHORT).show()
                }else{


                    if (usuarioExiste.contraseña != pass.toString()) {

                        var mensaje3 = "Contraseña incorrecta"
                        Toast.makeText(this, mensaje3, Toast.LENGTH_SHORT).show()
                    }else{

                        if (cbRecordarusuario.isChecked) {
                            var preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                            preferencias.edit().putString(resources.getString(R.string.nombre_usuario), usuario).apply()
                            preferencias.edit().putString(resources.getString(R.string.password_usuario), usuario).apply()
                        }
                        startMainActivity(usuario)

                    }


                }


            }

        }

    }

    private fun startMainActivity(usuario: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(resources.getString(R.string.nombre_usuario), usuario)
        startActivity(intent)
        finish()
    }
}