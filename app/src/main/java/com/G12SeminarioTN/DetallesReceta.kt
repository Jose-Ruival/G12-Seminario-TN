package com.G12SeminarioTN

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class DetallesReceta : AppCompatActivity() {

    lateinit var tv_nombre_detalle: TextView
    lateinit var tv_origen_detalle: TextView
    lateinit var tv_ingredientes_detalle: TextView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalles_receta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.name2)

        tv_nombre_detalle = findViewById(R.id.tv_nombre_detalle)
        tv_origen_detalle = findViewById(R.id.tv_origen_detalle)
        tv_ingredientes_detalle = findViewById(R.id.tv_ingredientes_detalle)

        val intent = getIntent()

            tv_nombre_detalle.text = intent.getStringExtra("nombre").toString()
            tv_origen_detalle.text = intent.getStringExtra("origen").toString()
            tv_ingredientes_detalle.text = intent.getStringExtra("ingredientes").toString()


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.btnListado){
            val intent = Intent(this,ListadoRecetaActivity::class.java)
            startActivity(intent)
        }
        if (item.itemId == R.id.btnIniciarSesion){
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}