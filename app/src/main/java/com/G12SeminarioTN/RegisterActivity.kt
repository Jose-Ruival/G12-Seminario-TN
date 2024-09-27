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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {

    lateinit var etUsuarioR: EditText
    lateinit var etPasswordR: EditText
    lateinit var etEmailR: EditText
    lateinit var btnRegistrarseR: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etEmailR = findViewById(R.id.etEmailR)
        etUsuarioR = findViewById(R.id.etUsuarioR)
        etPasswordR = findViewById(R.id.etPasswordR)
        btnRegistrarseR = findViewById(R.id.btnRegistrarR)



        btnRegistrarseR.setOnClickListener {

            var usuario =  etUsuarioR.text.toString()
            var contraseña= etPasswordR.text.toString()
            var email = etEmailR.text.toString()

            if (usuario.isEmpty() || contraseña.isEmpty() || email.isEmpty()){
                var mensaje= "Completar Datos"
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

            }else{
                var nuevoUsuario = Usuario(usuario, contraseña, email)
                UsuarioDatabase.getDatabase(applicationContext).usuarioDao().insert(nuevoUsuario)
                val intent = Intent(this, viewerterminosycondiciones::class.java)
                startActivity(intent)
            }
        }


    }
}