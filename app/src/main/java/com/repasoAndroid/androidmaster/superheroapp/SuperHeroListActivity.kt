package com.repasoAndroid.androidmaster.superheroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.repasoAndroid.androidmaster.databinding.ActivitySuperHeroListBinding
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.repasoAndroid.androidmaster.superheroapp.DetailSuperheroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class SuperHeroListActivity : AppCompatActivity() {
    //    No se inicializará immediatamente la variable binding
//    Cuidado con el nombre empieza en Activity y luego es el nombre de la clase
    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })
//        Le pasamos al adapter el código de la función LAMBDA en los {

        adapter = SuperheroAdapter { superheroId ->  navigateToDetail(superheroId) }
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter
    }

    private fun searchByName(query: String) {
//        Mostramos el circulo de progreso (típico cuando carga algo)
        binding.progressBar.isVisible = true
//        Lo que hagamos en esta llaves se hace en un hilo secundario, en este caso vamos a realizar
//        una petición a la API
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperheroes(query)
            if (myResponse.isSuccessful) {
                Log.i("aristidevs", "funciona")
//                Accedemos al body (donde está la respuesta)
                val response: SuperHeroDataResponse? = myResponse.body()

                if (response != null) {
                    Log.i("aristidevs", response.toString())

//                    CUIDADO: Como modificamos la UI, debemos hacerlo en el hilo principal
//                    Si lo hacmeos en la corrutina da error y peta
                    runOnUiThread {
                        adapter.updateList(response.superheroes)
//                        Ocultamos el círculo porque ya ha cargado algo
                        binding.progressBar.isVisible = false
                    }


                }
            } else {
                Log.i("aristidevs", "No funciona")
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Para acceder a los detalles de cada superheroe
    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}