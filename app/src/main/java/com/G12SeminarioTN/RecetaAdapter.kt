package com.G12SeminarioTN

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecetaAdapter (var recetas: MutableList<Receta>, var context: Context): RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    class RecetaViewHolder(view: View): RecyclerView.ViewHolder(view){

        lateinit var txtNombre: TextView
        lateinit var txtOrigen: TextView
        lateinit var txtIngredientes: TextView

        init {
            txtNombre = view.findViewById((TODO()))
            txtOrigen = view.findViewById((TODO()))
            txtIngredientes = view.findViewById((TODO()))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecetaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recetas, parent, false)
        return RecetaViewHolder(view)
    }

    override fun getItemCount() = recetas.size

    override fun onBindViewHolder(holder: RecetaViewHolder, position: Int) {
        val item = recetas.get(position)
        holder.txtNombre.text = item.nombre
        holder.txtOrigen.text = item.origen
        holder.txtIngredientes.text = item.ingredientes
    }
   }

