package com.G12SeminarioTN

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.G12SeminarioTN.API.Receta


class RecetaAdapter (var recetas: Array<Receta>, var context: Context): RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    class RecetaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var txtNombre: TextView
        lateinit var txtOrigen: TextView


        init {
            txtNombre = view.findViewById(R.id.tv_nombre)
            txtOrigen = view.findViewById(R.id.tv_origen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receta, parent, false)
        return RecetaViewHolder(view)
    }

    override fun getItemCount() = recetas.size

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val item = recetas.get(position)
        holder.txtNombre.text = item.name
        holder.txtOrigen.text = item.cuisine

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetallesReceta::class.java)
            intent.putExtra("nombre", item.name)
            intent.putExtra("origen", item.cuisine)
            intent.putExtra("calorias por porcion", item.caloriesPerServing)
            intent.putExtra("tiempo de preparacion", item.prepTimeMinutes)
            intent.putExtra("tiempo de coccion", item.cookTimeMinutes)
            intent.putStringArrayListExtra("ingredientes", ArrayList(item.ingredients))
            intent.putStringArrayListExtra("instrucciones", ArrayList(item.instructions))


            context.startActivity(intent)
        }
    }
}

