package com.repasoAndroid.androidmaster.sintaxis

fun main(){
    val weekDays= arrayOf("Lunes","MArtes","Miercoles","Jueves","Viernes","SAbado","Domingo")

    println (weekDays.size)

    if(weekDays.size >= 8){
        //println(weekDays[7])
    }else{
        //println("No hay más valores")
    }

    //Bucles para arrays
    for(position in weekDays.indices){
        println(weekDays[position])
    }

    for((position,value)in weekDays.withIndex()){
        println("La posicion $position, contiene $value")
    }
    for (weekDay in weekDays){
        println("Ahora es $weekDay")
    }
}