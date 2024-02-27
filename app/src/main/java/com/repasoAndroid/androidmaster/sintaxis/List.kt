package com.repasoAndroid.androidmaster.sintaxis

fun main(){
    mutableList()
}
fun mutableList(){
    var weekDays:MutableList<String> = mutableListOf("Lunes","MArtes","Miercoles","Jueves","Viernes","SAbado","Domingo")
    //Añade en la ultima posicion
    //weekDays.add("Nuevo día")

    //Añade a la posicion 0 y mueve los demás valores
    weekDays.add(0,"Nuevo día")

    if(weekDays.isEmpty()){

    }else{
        weekDays.forEach{println(it)}
    }

    if(weekDays.isNotEmpty()){
        weekDays.forEach{println(it)}
    }
    weekDays.last()

}
fun inmutableList(){
    val readOnly:List<String> = listOf("Lunes","MArtes","Miercoles","Jueves","Viernes","SAbado","Domingo")

    println(readOnly.size)
    //Por defecto hace readOnly.toString(). Por eso muestra toda la lista
    //println(readOnly)
    //println(readOnly[0])
    //Ultimo valor
    //println(readOnly.last())
    //println(readOnly.first())

    //Filtrar
    //Valores que tengan "a"
    //val example=readOnly.filter { it.contains("a") }
    //println(example)

    readOnly.forEach{ weekDay-> println(weekDay)}
}