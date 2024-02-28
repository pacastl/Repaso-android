package com.repasoAndroid.androidmaster.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.repasoAndroid.androidmaster.R
//para que no haga falta poner TaskCategory.
import com.repasoAndroid.androidmaster.todoapp.TaskCategory.*

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Personal,
        Other
    )

//    Este tipo de lista permite modifcar sus elementos. Cuando lo modificamos le decimos
//    al adapter que ha habido cambios y los pinta

    private val tasks = mutableListOf(
        Task("PruebaBusiness", Business),
        Task("PruebaPersonal", Personal),
        Task("PruebaOther", Other)
    )
    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
        initListeners()

    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
//        Le ponemos la vista
        dialog.setContentView(R.layout.dialog_task)
        //        Para darle la funcionalidad de añadir tareas
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)

//        Accedemos al texto del editText para guardarlo
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_dialog_category_business) -> TaskCategory.Business
                    getString(R.string.todo_dialog_category_personal) -> TaskCategory.Personal
                    else -> TaskCategory.Other
                }

//            Añadimos una nueva tarea pasandole el texto del editText y la categoría
                tasks.add(Task(currentTask, currentCategory))

                updateTasks()
//            Escondemos el diálogo al añadir la tarea
                dialog.hide()
            }

        }


//        Mostramos la vista
        dialog.show()
    }

    private fun initComponent() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) { position -> updateCategories(position) }
//        Layoutmanager se encarga de que la vista sea horizontal o vertical
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

//        No podemos poner un listener al adapter pero necesitamos ponerselo a cada uno de los items
//        Entonces recurrimos a una función LAMBDA
        tasksAdapter = TasksAdapter(tasks) { position -> onItemSelected(position) }
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    //    PAra modificar el item de la lista, recurrimos a una función LAMBDA
    private fun onItemSelected(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
//    Si no notificamos que ha habido cambios el adapter no los filtra
        updateTasks()
    }

    private fun updateCategories(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    //    Para avisar al adaptador del cambio en las tareas y que lo muestre
    private fun updateTasks() {
//        Coge las categories seleccionadas y filtra las tareas de cada una para mostrarlas
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTasks = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTasks
        tasksAdapter.notifyDataSetChanged()
    }
}