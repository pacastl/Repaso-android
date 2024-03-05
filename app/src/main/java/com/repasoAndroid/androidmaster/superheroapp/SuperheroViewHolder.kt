package com.repasoAndroid.androidmaster.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.repasoAndroid.androidmaster.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View):RecyclerView.ViewHolder(view) {

//    ViewBinding
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superheroItemResponse: SuperheroItemResponse,  onItemSelected: (String) -> Unit
    ){
        binding.tvSuperheroName.text=superheroItemResponse.name
//        Cargamos la imagen al ImageView
        binding.ivSuperhero
//        Cargamos la imagen en el ImageView
        Picasso.get().load(superheroItemResponse.superheroImage.url).into(binding.ivSuperhero)
//        root es toda la vista, así que está atento a si se clickea en cualquier sitio
        binding.root.setOnClickListener{onItemSelected(superheroItemResponse.superheroId)}
    }
}
