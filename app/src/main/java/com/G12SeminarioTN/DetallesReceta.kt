package com.G12SeminarioTN

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.os.Handler
import android.os.Looper
import com.G12SeminarioTN.API.Ingredient


class DetallesReceta : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var tv_nombre_detalle: TextView
    lateinit var tv_origen_detalle: TextView
    lateinit var tv_ingredientes_detalle: TextView
    private var mediaPlayer: MediaPlayer? = null
    private var isDownloading = false // Estado de descarga
    private var descargaCompletada = false // Estado de si ya se completó una descarga


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_receta)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.name2)


        tv_nombre_detalle = findViewById(R.id.tv_nombre_detalle)
        tv_origen_detalle = findViewById(R.id.tv_origen_detalle)
        tv_ingredientes_detalle = findViewById(R.id.tv_ingredientes_detalle)


        val intent = intent
        tv_nombre_detalle.text = intent.getStringExtra("nombre").toString()
        tv_origen_detalle.text = intent.getStringExtra("origen").toString()
        tv_ingredientes_detalle.text = (intent.getSerializableExtra("ingredientes") as? ArrayList<Ingredient>).toString()
       // tv_ingredientes_detalle.text = intent.getStringExtra("ingredientes").toString()
    }


    private fun reproducirMusica() {
        // Crear un hilo para reproducir música
        Thread {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.mi_musica)
                mediaPlayer?.isLooping = true // Asegura que la música se repite
                mediaPlayer?.start() // Inicia la música
            } else if (!mediaPlayer!!.isPlaying) {
                runOnUiThread {
                    mediaPlayer?.start() // Inicia la música solo si no está reproduciéndose
                }
            }
        }.start()
    }




    private fun detenerMusica() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
            mediaPlayer = null // Libera el MediaPlayer
        }
    }


    private fun iniciarSimulacionDescarga() {
        // Si ya está en proceso de descarga, mostrar mensaje
        if (isDownloading) {
            Toast.makeText(this, "Descarga en curso. Espera a que termine.", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // Si ya se ha completado una descarga, mostrar diálogo para confirmar si se quiere volver a descargar
        if (descargaCompletada) {
            mostrarDialogoConfirmacionDescarga()
            return
        }
        // Si no hay descarga en curso ni se ha completado una descarga, comenzar una nueva descarga
        ejecutarDescarga()
    }


    private fun mostrarDialogoConfirmacionDescarga() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Descarga completada")
        builder.setMessage("Ya se descargó esta receta. ¿Quieres volver a descargarla?")
        builder.setPositiveButton("Sí") { dialog, _ ->
            ejecutarDescarga() // Ejecutar la descarga si el usuario confirma
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }


    private fun ejecutarDescarga() {
        isDownloading = true // Marca que la descarga ha comenzado
        descargaCompletada = false // Reinicia el estado de finalización de la descarga
        disableDownloadButton(true) // Deshabilita el botón de descarga


        reproducirMusica() // Inicia la música al comenzar la descarga


        // Crear un hilo para simular la descarga
        Thread {
            for (i in 1..5) { // 5 iteraciones para simular 5 segundos
                Thread.sleep(1000) // Simula un segundo de descarga
                runOnUiThread {
                    Toast.makeText(this, "Descargando... $i", Toast.LENGTH_SHORT).show()
                }
            }
            // Una vez finalizada la simulación de descarga
            runOnUiThread {
                isDownloading = false // Marca que la descarga ha terminado
                descargaCompletada = true // Marca que se completó la descarga
                Toast.makeText(this, "Descarga finalizada.", Toast.LENGTH_SHORT).show()
                disableDownloadButton(false) // Habilita el botón de descarga
            }
            // Detener la música 1 segundo después de la simulación
            Handler(Looper.getMainLooper()).postDelayed({
                detenerMusica() // Detener la música después de la simulación
            }, 5500) // Detener la música después de 6 segundos
        }.start()
    }


    private fun disableDownloadButton(disable: Boolean) {
        // Aquí deshabilitamos o habilitamos el botón de descarga
        val menuItem = toolbar.menu.findItem(R.id.btnDescargar)
        menuItem.isEnabled = !disable // Si disable es true, el botón se deshabilita
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



