package com.G12SeminarioTN

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.G12SeminarioTN.Usuario.Usuario
import com.G12SeminarioTN.Usuario.UsuarioDatabase

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

        // Agregar los fragments de error
        agregarFragmentError(R.id.errorEmailContainer)
        agregarFragmentError(R.id.errorUsuarioContainer)
        agregarFragmentError(R.id.errorPasswordContainer)



        btnRegistrarseR.setOnClickListener {

            val usuario = etUsuarioR.text.toString()
            val contraseña = etPasswordR.text.toString()
            val email = etEmailR.text.toString()

            // Limpiar errores antes de la validación
            limpiarErrores()

            var hayError = false

            // Validación de campos
            if (email.isEmpty()) {
                val errorFragmentEmail = supportFragmentManager.findFragmentById(R.id.errorEmailContainer) as? ErrorFragment
                errorFragmentEmail?.mostrarError()
                hayError = true
            }

            if (usuario.isEmpty()) {
                val errorFragmentUsuario = supportFragmentManager.findFragmentById(R.id.errorUsuarioContainer) as? ErrorFragment
                errorFragmentUsuario?.mostrarError()
                hayError = true
            }

            if (contraseña.isEmpty()) {
                val errorFragmentPassword = supportFragmentManager.findFragmentById(R.id.errorPasswordContainer) as? ErrorFragment
                errorFragmentPassword?.mostrarError()
                hayError = true
            }

            // Si no hay errores, crear el usuario
            if (!hayError) {
                val nuevoUsuario = Usuario(usuario, contraseña, email)
                UsuarioDatabase.getDatabase(applicationContext).usuarioDao().insert(nuevoUsuario)
                val intent = Intent(this, viewerterminosycondiciones::class.java)
                startActivity(intent)
            }
        }
    }

    private fun limpiarErrores() {
        val errorFragmentEmail = supportFragmentManager.findFragmentById(R.id.errorEmailContainer) as? ErrorFragment
        val errorFragmentUsuario = supportFragmentManager.findFragmentById(R.id.errorUsuarioContainer) as? ErrorFragment
        val errorFragmentPassword = supportFragmentManager.findFragmentById(R.id.errorPasswordContainer) as? ErrorFragment

        errorFragmentEmail?.ocultarError()
        errorFragmentUsuario?.ocultarError()
        errorFragmentPassword?.ocultarError()
    }

    private fun agregarFragmentError(containerId: Int) {
        val fragment = ErrorFragment()
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }
}