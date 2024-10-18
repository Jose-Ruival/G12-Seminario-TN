package com.G12SeminarioTN

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)

        verBienvenida()

        // Temporizador para la pantalla de splash
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                // Llamada a la función para iniciar LoginActivity
                startLoginActivity()
            }
        }.start()
    }

    private fun verBienvenida() {

    }

    // Función para iniciar LoginActivity
    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity() // Cierra todas las actividades previas
    }
}

