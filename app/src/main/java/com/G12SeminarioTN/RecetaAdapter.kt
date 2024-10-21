package com.G12SeminarioTN

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.G12SeminarioTN.API.Hit
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecetaAdapter(var recetas: List<Hit>, var context: Context): RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    class RecetaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var txtNombre: TextView
        lateinit var txtOrigen: TextView
        lateinit var txtCalorias: TextView
        lateinit var iv_imagen: ImageView


        init {
            txtNombre = view.findViewById(R.id.tv_nombre)
            txtCalorias = view.findViewById(R.id.tv_calorias_detalle)
            iv_imagen = view.findViewById(R.id.iv_imagen)
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
        holder.txtCalorias.text = item.recipe.calories.toString()
        Picasso.get().load(item.recipe.image).into(holder.iv_imagen)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetallesReceta::class.java)
            intent.putExtra("nombre", item.recipe.label)
            intent.putExtra("ingredientes", ArrayList(item.recipe.ingredients))
            intent.putExtra("identificador del sitio de origen", item.recipe.source)
            intent.putExtra("url", item.recipe.url)
            intent.putExtra("porciones", item.recipe.yield)
            intent.putExtra("calorias totales", item.recipe.calories)
            intent.putStringArrayListExtra("cuisineType", ArrayList(item.recipe.cuisineType))
            context.startActivity(intent)
        }
    }
}

