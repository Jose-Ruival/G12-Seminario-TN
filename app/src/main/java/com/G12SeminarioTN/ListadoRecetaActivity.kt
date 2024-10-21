package com.G12SeminarioTN


import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.G12SeminarioTN.API.Recetas
import com.G12SeminarioTN.API.RetroFitClient
import com.G12SeminarioTN.API.RetroFitClient.apiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListadoRecetaActivity : AppCompatActivity() {

    private lateinit var bt_lista: Button
    private lateinit var et_lista: EditText
    private lateinit var rg_tipo: RadioGroup
    private lateinit var rg_origen1: RadioGroup
    private lateinit var rg_origen2: RadioGroup
    private lateinit var rvReceta: RecyclerView
    private lateinit var recetaAdapter: RecetaAdapter
    private lateinit var toolbar: Toolbar
    private var mediaPlayer: MediaPlayer? = null
    private var descargaCompletada = false
    private var isDownloading = false // Estado de descarga

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listado_receta)
        val appId = "595e6eef"
        val appKey = "51ea5099c29787124e81371d2592b8f0"

        val apiService = RetroFitClient.apiService

        rg_tipo = findViewById(R.id.rg_tipo)
        rg_origen1 = findViewById(R.id.rg_origen1)
        rg_origen2 = findViewById(R.id.rg_origen2)
        bt_lista = findViewById(R.id.bt_lista)
        et_lista = findViewById(R.id.et_lista)
        rvReceta = findViewById(R.id.rv_recetas)
        recetaAdapter = RecetaAdapter(emptyList(), this@ListadoRecetaActivity)
        rvReceta.adapter = recetaAdapter

        rg_origen1.setOnCheckedChangeListener { group, checkedId ->
            var id: Int = rg_origen2.checkedRadioButtonId
            if (id!=-1){
                rg_origen2.clearCheck()
            }
        }

        rg_origen2.setOnCheckedChangeListener { group, checkedId ->
            var id: Int = rg_origen1.checkedRadioButtonId
            if (id!=-1){
                rg_origen1.clearCheck()
            }
        }

        bt_lista.setOnClickListener(){
            var idOrigen: Int = -1
            var textoOrigen: String
            var idTipo: Int = -1
            var textoTipo: String

            if(rg_origen1.checkedRadioButtonId != -1){
                idOrigen = rg_origen1.checkedRadioButtonId
            }

            if(rg_origen2.checkedRadioButtonId != -1){
                idOrigen = rg_origen2.checkedRadioButtonId
            }

            if(idOrigen != -1) {
                val rbOrigen = findViewById<RadioButton>(idOrigen)
                textoOrigen = rbOrigen.text.toString()
                Log.d("ASDADSDAD", textoOrigen)
            } else textoOrigen = ""

            if(rg_tipo.checkedRadioButtonId != -1){
                idTipo = rg_tipo.checkedRadioButtonId
            }

            if(idTipo != -1) {
                val rbTipo = findViewById<RadioButton>(idTipo)
                textoTipo = rbTipo.text.toString()
                Log.d("ASDADSDAD", textoTipo)
            } else textoTipo = ""

            val busqueda = et_lista.text.toString()
            buscarRecetas(appId,appKey,busqueda,0,20,textoTipo,textoOrigen)
        }

        // Configuración de la interfaz
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.name2)


    }

    private fun buscarRecetas(appId: String, appKey: String, query: String, from: Int, to: Int, mealType: String, cuisineType: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val call: Call<Recetas>
            if (mealType != "" && cuisineType != ""){
                call = apiService.searchRecipes(appId, appKey, query, from, to, mealType, cuisineType)
            } else if(mealType == "" && cuisineType != ""){
                call = apiService.searchRecipes(appId, appKey, query, from, to, null, cuisineType)
            } else if(mealType != "" && cuisineType == ""){
                call = apiService.searchRecipes(appId, appKey, query, from, to, mealType, null)
            } else call = apiService.searchRecipes(appId, appKey, query, from, to, null, null)
            call.enqueue(object : Callback<Recetas> {
                override fun onResponse(call: Call<Recetas>, response: Response<Recetas>) {
                    if (response.isSuccessful && response.body() != null) {
                        val recetas = response.body()
                        if (recetas != null) {
                            recetaAdapter = RecetaAdapter(recetas.hits, this@ListadoRecetaActivity)
                        }
                        rvReceta.adapter = recetaAdapter
                    } else Log.e("NO FUNCIONA", "ASDASDASDAS")
                }

                override fun onFailure(call: Call<Recetas>, t: Throwable) {
                    Log.e("Error", t.message ?: "Error desconocido")
                }
            })
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnIniciarSesion -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.btnListado -> {
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
