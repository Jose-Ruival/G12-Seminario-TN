package com.G12SeminarioTN

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class ListadoRecetaActivity : AppCompatActivity() {

    lateinit var  rvReceta: RecyclerView
    lateinit var recetaAdapter: RecetaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_receta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        rvReceta = findViewById(R.id.rv_recetas)
        recetaAdapter = RecetaAdapter(getRecetas(), this)
        rvReceta.adapter = recetaAdapter
    }
}

private fun getRecetas(): MutableList<Receta> {
    val recetas: MutableList<Receta> = ArrayList()
    recetas.add(Receta(1,"Pastafrola","Argentina","Manteca, Huevos, Harina, Azucar, Membrillo"))
    recetas.add(Receta(2,"Huevo duro", "Gallina", "Huevo"))
    recetas.add(Receta(3,"Pancho","Constitucion","Salchicha, Pan"))
    recetas.add(Receta(4,"Arroz con Queso","Belgica","Arroz, Queso, Aceite"))
    recetas.add(Receta(5,"Pochoclos", "Cine", "Maiz, Azucar, Aceite"))
    recetas.add(Receta(6,"Omelette", "Francia","Huevo, Queso, Jamon, Manteca"))
    return recetas
}