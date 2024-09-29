package com.G12SeminarioTN

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class ListadoRecetaActivity : AppCompatActivity() {

    lateinit var rvReceta: RecyclerView
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
    recetas.add(Receta(1,"Pastafrola","Argentina","Masa:\n" +
            "\n" +
            "Huevo 1\n" +
            "Yema 1\n" +
            "Harina leudante 400 gr\n" +
            "Azúcar 200 gr\n" +
            "Manteca 200 gr\n" +
            "Esencia de vainilla 1cda\n" +
            "Sal 1 cdta\n" +
            "Relleno:\n" +
            "\n" +
            "Dulce de membrillo 200 grs\n" +
            "Dulce de batata 200 grs\n" +
            "Agua c/n"))
    recetas.add(Receta(2,"Milanesas", "Argentina", "2 huevos\n" +
            "1 cda. de mostaza de Dijon\n" +
            "Jugo y ralladura de 1 limón\n" +
            "1 diente de ajo\n" +
            " Perejil picado\n" +
            "1 cda. de queso rallado\n" +
            "Sal y pimienta"))
    recetas.add(Receta(3,"Ramen","Japon","1 Puerro (solo la parte verde)\n" +
            "1 Cebolla chica (o 1/2 grande)\n" +
            "4 Rodajas de jengibre fresco\n" +
            "1/2 Zanahoria\n" +
            "4 Dientes de ajo\n" +
            "2 Huevos\n" +
            "1 Costilla de cerdo (puede ser otra parte)\n" +
            "Fideos de arroz (pueden ser otros)\n" +
            "1 Vaso de Salsa de soja\n" +
            "1 Cda. de sake (yo: vodka)\n" +
            "3 Cdas. de mirín (yo: vino blanco dulce)\n" +
            "2 Cditas. de azúcar\n" +
            "1 Cebolla de verdeo\n" +
            "Sal"))
    recetas.add(Receta(4,"Chop Suey de pollo","China","500 g de pechuga de pollo cortada en tiras\n" +
            "1 zanahoria grande cortada en tiras\n" +
            "1/2 pimentón rojo cortado en tiras\n" +
            "1 taza de de porotos verdes  (chauchas, judías, ejotes) (200 g)\n" +
            "1 cebolla cortada en rodajas finas\n" +
            "1 taza de coliflor cortado (200 g)\n" +
            "1 taza de brócoli cortado (200 g )\n" +
            "1 taza de brotes de soja (200 g)\n" +
            "1 taza de champiñones fileteados (200 g)\n" +
            "2 cucharadas de salsa de soya\n" +
            "1 cucharadita de jengibre fresco rallado\n" +
            "2 dientes de ajo picados\n" +
            "1 cucharada de maicena disuelta en 1/4 taza de agua\n" +
            "Aceite para saltear\n" +
            "1 cucharada de aceite de sésamo (opcional)\n" +
            "Sal y pimienta a gusto"))
    recetas.add(Receta(5,"Chopitos a la Andaluza", "España", "500 gramos de chopitos frescos o puntillitas\n" +
            "100 gramos de harina de trigo (se puede mezclar con harina de garbanzo o maíz)\n" +
            "Aceite de oliva virgen extra para freír\n" +
            "Sal a gusto\n" +
            "Rodajas de limón (opcional)"))
    recetas.add(Receta(6,"Curry", "Tailandia","2 cucharadas de aceite vegetal\n" +
            "2 cucharadas de pasta de curry tailandés (según la preferencia de picante)\n" +
            "400 ml de leche de coco\n" +
            "500 gr de proteína (pollo, camarones, tofu, etc.), cortada en trozos\n" +
            "1 pimiento rojo, cortado en tiras\n" +
            "1 berenjena tailandesa, cortada en cubos\n" +
            "1 zanahoria, cortada en rodajas\n" +
            "400 gr de brotes de bambú, escurridos\n" +
            "2 cucharaditas de salsa de pescado\n" +
            "1 cucharadita de azúcar de palma (o azúcar moreno)\n" +
            "Hojas de albahaca tailandesa y cilantro fresco para decorar\n" +
            "Jugo de lima a gusto"))
    return recetas
}