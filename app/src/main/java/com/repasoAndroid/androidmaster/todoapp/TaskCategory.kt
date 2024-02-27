package com.repasoAndroid.androidmaster.todoapp

//isSelected es un atributo que se aplica a cada objeto de la sealed class (en los object estaría mal puesto)
sealed class TaskCategory(var isSelected:Boolean = true) {
    //    data class viene bien cuando cada sealed clas tiene atributos distintos pero no es el caso
    object Personal : TaskCategory()
    object Business : TaskCategory()
    object Other : TaskCategory()
}