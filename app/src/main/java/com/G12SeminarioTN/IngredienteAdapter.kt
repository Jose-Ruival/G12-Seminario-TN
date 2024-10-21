package com.G12SeminarioTN

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.G12SeminarioTN.API.Ingredient
import com.G12SeminarioTN.RecetaAdapter.RecetaViewHolder
import com.squareup.picasso.Picasso

class IngredienteAdapter(var ingredientes: List<Ingredient>, var context: Context): RecyclerView.Adapter<IngredienteAdapter.IngredienteViewHolder>()  {

    class IngredienteViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        lateinit var tv_ingrediente: TextView
        lateinit var tv_weight: TextView

        init {
            tv_ingrediente = view.findViewById(R.id.tv_ingrediente)
            tv_weight = view.findViewById(R.id.tv_weight)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingrediente, parent, false)
        return IngredienteViewHolder(view)
    }

    override fun getItemCount() = ingredientes.size

    override fun onBindViewHolder(holder: IngredienteViewHolder, position: Int) {
        val item = ingredientes.get(position)
        holder.tv_ingrediente.text = item.food
        holder.tv_weight.text = item.weight.toString()

    }
}