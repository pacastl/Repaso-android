package com.repasoAndroid.androidmaster.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.repasoAndroid.androidmaster.R

// private val onTaskSelected:(Int)->Unit es la función LAMBDA que devuelve un Int
class TasksAdapter(var tasks: List<Task>, private val onTaskSelected: (Int) -> Unit) :
    RecyclerView.Adapter<TasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)
        return TasksViewHolder(view)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])
        //        itemView es toda la celda, el contenido
        //        llamamos a la función LAMBDA

        holder.itemView.setOnClickListener { onTaskSelected(position) }
    }
}