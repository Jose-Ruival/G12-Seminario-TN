package com.G12SeminarioTN

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ErrorFragment : Fragment() {

    private lateinit var tvError: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_error, container, false)
        tvError = view.findViewById(R.id.tvError) // Obtiene el TextView del layout
        return view
    }

    // Mostrar el mensaje de error
    fun mostrarError() {
        tvError.visibility = View.VISIBLE
    }

    // Ocultar el mensaje de error
    fun ocultarError() {
        tvError.visibility = View.GONE
    }
}

