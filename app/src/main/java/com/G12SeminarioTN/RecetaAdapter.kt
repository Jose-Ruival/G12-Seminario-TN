package com.G12SeminarioTN

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.G12SeminarioTN.API.Hit


class RecetaAdapter(var recetas: List<Hit>, var context: Context): RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    class RecetaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var txtNombre: TextView
        lateinit var txtOrigen: TextView
        lateinit var txtCalorias: TextView


        init {
            txtNombre = view.findViewById(R.id.tv_nombre)
            //txtOrigen = view.findViewById(R.id.tv_origen)
            txtCalorias = view.findViewById(R.id.tv_calorias)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        return RecetaViewHolder(view)
    }

    override fun getItemCount() = recetas.size

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val item = recetas.get(position)
        holder.txtNombre.text = item.recipe.label
       // holder.txtOrigen.text = item.recipe.toString()
        holder.txtCalorias.text = item.recipe.calories.toString()


        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetallesReceta::class.java)
            intent.putExtra("nombre", item.recipe.label)
            //aca va ingredientes, lo agregue con Serializable
            intent.putExtra("ingredientes", ArrayList(item.recipe.ingredients))
            intent.putExtra("identificador del sitio de origen", item.recipe.source)
            intent.putExtra("url", item.recipe.url)
            intent.putExtra("porciones", item.recipe.yield)
            intent.putExtra("calorias totales", item.recipe.calories)
            intent.putStringArrayListExtra("cuisineType", ArrayList(item.recipe.cuisineType))
            //agrego receta??
            //que es cuisineType??
            //intent.putExtra("origen", item.recipe.label)


            context.startActivity(intent)
        }
    }
}

