package com.repasoAndroid.androidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.repasoAndroid.androidmaster.R

//el adapter es el que nos permite mostrar las listas
//usamos otra función LAMBDA
class CategoriesAdapter(private val categories:List<TaskCategory>,private val onItemSelected:(Int)->Unit):RecyclerView.Adapter<CategoriesViewHolder>() {

//    Crea una vista visual y la monta para que el método onBindViewHolder pueda pasarle la info que tiene que pintar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_task_category,parent,false)
//        Devuelve un viewholder para cada uno de los items, este viewHolder contiene todo el layout
    return CategoriesViewHolder(view)
    }

//    Es como si fuese return categories.size
    override fun getItemCount()= categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
//        holder es como llamar a cada uno de los items
        holder.render(categories[position], onItemSelected)

    }
}