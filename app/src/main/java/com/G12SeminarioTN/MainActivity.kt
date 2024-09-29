package com.G12SeminarioTN

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.G12SeminarioTN.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
class MainActivity : AppCompatActivity() {




    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        enableEdgeToEdge()


        // Infla el layout utilizando el objeto de binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        replaceFragment(Inicio())




        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Inicio -> replaceFragment(Inicio())
                R.id.Buscar-> replaceFragment(Buscar())
                R.id.Añadir-> replaceFragment(Anadir())
                R.id.Perfil -> replaceFragment(Perfil())


                else -> {}
            }
            true
        }




        saludarUsuario()


        val intent = Intent(this, ListadoRecetaActivity::class.java)
        startActivity(intent)
    }




    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }




    private fun saludarUsuario() {
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val nombreUsuario = bundle.getString(resources.getString(R.string.nombre_usuario))
            Toast.makeText(this, "Bienvenido/a $nombreUsuario", Toast.LENGTH_SHORT).show()
        }
    }
}

