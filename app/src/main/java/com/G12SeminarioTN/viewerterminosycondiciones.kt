package com.G12SeminarioTN

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class viewerterminosycondiciones : AppCompatActivity() {
    lateinit var checkBox: CheckBox
    lateinit var boton_aceptar_tyc : Button
    lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_viewerterminosycondiciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        toolbar= findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.name2)


        // Referencia a los elementos en el archivo XML
        checkBox = findViewById(R.id.checkBox)
        boton_aceptar_tyc =findViewById(R.id.boton_aceptar_tyc)

        // Inicialmente, el botón está deshabilitado
        boton_aceptar_tyc .isEnabled = false

        // Listener para cuando cambie el estado del CheckBox
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            // El botón se habilita solo cuando el CheckBox está marcado
            boton_aceptar_tyc.isEnabled = isChecked
        }

        boton_aceptar_tyc.setOnClickListener(){
            val intent = Intent(this, ListadoRecetaActivity::class.java)
            startActivity(intent)
        }
    }
}
