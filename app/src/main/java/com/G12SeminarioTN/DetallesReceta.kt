package com.G12SeminarioTN

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
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
    private var mediaPlayer: MediaPlayer? = null
    private var isDownloading = false // Estado de descarga

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

        val intent = intent
        tv_nombre_detalle.text = intent.getStringExtra("nombre").toString()
        tv_origen_detalle.text = intent.getStringExtra("origen").toString()
        tv_ingredientes_detalle.text = intent.getStringExtra("ingredientes").toString()
    }

    private fun iniciarSimulacionDescarga() {
        if (isDownloading) {
            Toast.makeText(this, "Descarga en curso. Espera a que termine.", Toast.LENGTH_SHORT).show()
            return
        }
        isDownloading = true // Marcar que la descarga ha comenzado

        // Crear un hilo para la simulación de descarga
        Thread {
            reproducirMusica() // Iniciar la música cuando comienza la descarga

            // Simular una descarga (espera 5 segundos como ejemplo)
            for (i in 1..5) {
                Thread.sleep(1000) // Simula un segundo de descarga
                runOnUiThread {
                    Toast.makeText(this, "Descargando... $i", Toast.LENGTH_SHORT).show()
                }
            }

            // Una vez finalizada la simulación de descarga, detener la música
            runOnUiThread {
                detenerMusica()
                isDownloading = false // Marcar que la descarga ha terminado
            }
        }.start()
    }

    private fun reproducirMusica() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.mi_musica)
            mediaPlayer?.start()

            // Detener la música después de 5 segundos
            Thread {
                Thread.sleep(5000) // Tiempo de descarga
                runOnUiThread {
                    detenerMusica() // Detener la música después de la descarga
                }
            }.start()
        }
    }

    private fun detenerMusica() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        detenerMusica() // Asegúrate de liberar el recurso al destruir la actividad
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnListado -> {
                val intent = Intent(this, ListadoRecetaActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.btnIniciarSesion -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.btnDescargar -> {
                iniciarSimulacionDescarga() // Llama a la función para simular descarga
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
