package com.repasoAndroid.androidmaster.sintaxis

fun main(){
    ifMultiple()
}

fun ifMultipleOR(){
    var pet = "dog"
    var isHappy= true
    if (pet== "dog" || (pet=="cat" && isHappy)){
        println("Es un gato o un perro")
    }
}

fun ifMultiple(){
    var age = 18
    var parentPermission = true

    if (age >= 18 && parentPermission){
        println("Puedo beber")
    }else{
        println("No puedo beber")
    }
}

fun ifInt(){
    var age = 29

    if (age >= 18){
        print ("Ya puedes beber alcohol")
    }else{
        print ("NO puedes beber alcohol")
    }
}

fun ifBoolean(){
    var soyFeliz:Boolean = false

    //Con ! es ==false
    //Sin nada ==true
    if (!soyFeliz){
        println("Estoy triste :(")
    }
}
fun ifAnidado(){
    val animal= "Aris"

    if(animal=="dog"){
        println("Es un perro")
    }else if(animal=="cat"){
        println("Es un gato")
    }else if(animal=="bird"){
        println("Es un pajaro")
    }else{
        println("No es un uno de los anteriores")
    }
}

fun ifBasico(){
    val name = "Aris"

    if(name == "Aris"){
        println("Oye la variable name is Aris")
    }else{
        println("No es Aris")
    }
}