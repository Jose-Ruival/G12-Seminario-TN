package com.G12SeminarioTN

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class LoginActivity : AppCompatActivity() {

    lateinit var etUsuario: EditText
    lateinit var etPassword: EditText
    lateinit var cbRecordarUsuario: CheckBox
    lateinit var btnRegistrarse: Button
    lateinit var btnIniciarSesion: Button

    private val CHANNEL_ID = "canal_recordar_usuario"
    private val NOTIFICATION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Crear canal de notificación
        crearCanalDeNotificacion()

        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        cbRecordarUsuario = findViewById(R.id.cbRecordarUsuario)
        btnRegistrarse = findViewById(R.id.btnRegistrar)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)

        // Almacenar credenciales si el checkbox está marcado
        cbRecordarUsuario.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mostrarNotificacion("Recordar usuario", "¿Querés que recordemos tu usuario?")
            }
        }

        // Lógica para el botón de iniciar sesión
        btnIniciarSesion.setOnClickListener {
            var usuario = etUsuario.text.toString()
            var pass = etPassword.text.toString()

            if (usuario.isEmpty() || pass.isEmpty()) {
                val mensaje = "Completar Datos"
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
            } else {
                val db = UsuarioDatabase.getDatabase(this)
                val usuarioDao = db.usuarioDao()

                val usuarioExiste = usuarioDao.getUsuarioNombre(usuario)

                if (usuarioExiste == null) {
                    val mensaje2 = "El Usuario no Existe"
                    Toast.makeText(this, mensaje2, Toast.LENGTH_SHORT).show()
                } else {
                    if (usuarioExiste.contraseña != pass.toString()) {
                        val mensaje3 = "Contraseña incorrecta"
                        Toast.makeText(this, mensaje3, Toast.LENGTH_SHORT).show()
                    } else {
                        if (cbRecordarUsuario.isChecked) {
                            val preferencias = getSharedPreferences(resources.getString(R.string.sp_credenciales), MODE_PRIVATE)
                            preferencias.edit().putString(resources.getString(R.string.nombre_usuario), usuario).apply()
                            preferencias.edit().putString(resources.getString(R.string.password_usuario), pass).apply()
                        }
                        iniciarMainActivity(usuario)
                    }
                }
            }
        }

        // Configuración de botón para registrarse
        btnRegistrarse.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Crear el canal de notificaciones
    private fun crearCanalDeNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nombre = "Canal Recordar Usuario"
            val descripcion = "Canal para notificaciones de recordar usuario"
            val importancia = NotificationManager.IMPORTANCE_HIGH
            val canal = NotificationChannel(CHANNEL_ID, nombre, importancia).apply {
                description = descripcion
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(canal)
        }
    }

    @SuppressLint("MissingPermission")
    private fun mostrarNotificacion(titulo: String, contenido: String) {

        val intentMainActivity = Intent(this, MainActivity::class.java)
        val pendingIntentMainActivity: PendingIntent = PendingIntent.getActivity(this, 0, intentMainActivity, PendingIntent.FLAG_IMMUTABLE)

        val imagenGrande = BitmapFactory.decodeResource(resources, R.drawable.logo)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle(titulo)
            .setContentText(contenido)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setLargeIcon(imagenGrande)
            .addAction(R.drawable.baseline_check_24, "Aceptar", pendingIntentMainActivity)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    // Navegar a la pantalla principal
    private fun iniciarMainActivity(usuario: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
        finish()
    }

}




