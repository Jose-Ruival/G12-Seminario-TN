package com.G12SeminarioTN

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallesReceta : AppCompatActivity() {

    lateinit var tv_nombre_detalle: TextView
    lateinit var tv_origen_detalle: TextView
    lateinit var tv_ingredientes_detalle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_receta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tv_nombre_detalle = findViewById(R.id.tv_nombre_detalle)
        tv_origen_detalle = findViewById(R.id.tv_origen_detalle)
        tv_ingredientes_detalle = findViewById(R.id.tv_ingredientes_detalle)

        val intent = getIntent()

            tv_nombre_detalle.text = intent.getStringExtra("nombre").toString()
            tv_origen_detalle.text = intent.getStringExtra("origen").toString()
            tv_ingredientes_detalle.text = intent.getStringExtra("ingredientes").toString()


    }

}