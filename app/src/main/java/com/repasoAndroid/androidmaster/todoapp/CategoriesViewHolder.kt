package com.repasoAndroid.androidmaster.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.repasoAndroid.androidmaster.R

//Es basicamente donde pintamos
class CategoriesViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val tvCategoryName:TextView=view.findViewById(R.id.tvCategoryName)
    private val divider:View = view.findViewById(R.id.divider)
    private val viewContainer:CardView=view.findViewById(R.id.viewContainer)

    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit){

        val color = if(taskCategory.isSelected){
            R.color.todo_background_card
        }else{
            R.color.todo_background_disabled
        }

        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context,color))

        itemView.setOnClickListener{ onItemSelected(layoutPosition)}

//        Porque es una sealed class
        when(taskCategory){
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
//                Le cambiamos el color al divider (la linea horizontal dentro de cada tarjeta)
                divider.setBackgroundColor(
//                    Cualquier componentes tiene el contexto en su interior
                    ContextCompat.getColor(divider.context,R.color.todo_business_category)
                )
            }
            TaskCategory.Other -> {
                tvCategoryName.text="Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context,R.color.todo_other_category)
                )
            }
            TaskCategory.Personal -> {
                tvCategoryName.text="Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context,R.color.todo_personal_category)
                )
            }
        }
    }
}