package com.G12SeminarioTN

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class RecetaAdapter (var recetas: MutableList<Receta>, var context: Context): RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder>() {

    class RecetaViewHolder(view: View): RecyclerView.ViewHolder(view){

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
        holder.txtNombre.text = item.nombre
        holder.txtOrigen.text = item.origen

        holder.itemView.setOnClickListener{
            Toast.makeText(context, "Ingredientes: " + item.ingredientes, Toast.LENGTH_SHORT).show()
        }

    }
   }

