package com.repasoAndroid.androidmaster.sintaxis

//Es una vairable de clase--> se puede acceder en toda la clase
val age: Int = 30

/*
VARIABLES
 */
fun main() {

    //Llamamos a la funcion
    showMyName()
    showMyAge( 25)
    add(25, 76)
    val mySubstract= substract(10, 5)
    println(mySubstract)
}

//Las variables dentro de esta función solo se pueden acceder dentro de esta función
fun variablesNumericas() {
    /*
VARIABLES numéricas
*/
    //Int--> Si sabemos que el valor está dentro de su rango
    //usar Int en vez de Long para optimizar la memoria

    var age2: Int = 30

    //Long
    val example: Long = 30

    //Float--> similar a las anteriores pero soporta hasta 6 decimales
    //Para que float admita un valor hay que ponerle "f" al final
    val floatExample: Float = 30.5f

    //Double--> como float ero admite más decimales, creo que 12
    val doubleExample: Double = 3241.3123123

}

fun variablesBoolean() {
    /*
Variables booleanas
 */
    val booleanExample: Boolean = true
    val booleanExample2: Boolean = false
}

fun variblesAlfaNumericas() {
    /*
VARIABLES alfanuméricas
*/
    //Char --> entre ''
    val charExample: Char = 'a'

    //String --> entre ""
    val stringExample: String = "Saludos humano"

    val stringExample2 = "4"
    val stringExample3 = "23"
    //Si estamos seguros de que el string contiene un nº podemos sumalos al convertirlo a int con .toInt()
    println(stringExample2.toInt() + stringExample3.toInt())

    var stringConcatenada: String = "Hola nº "
    stringConcatenada = "Hola nº $stringExample2, tienes $stringExample3 euros?"
    println(stringConcatenada)


    val example123: String = age.toString()


    print(stringExample)
}

fun showMyName() {
    println("Me llamo fulanito")
}

fun showMyAge(currentAge: Int = 4) {
    println("Tengo $currentAge años")
}

fun add(firstNumber: Int, secondNumber: Int) {
    println(firstNumber + secondNumber)
}

//Recibe 2 enteros devuelve 1
fun substract(firstNumber: Int, secondNumber: Int):Int {
    return firstNumber - secondNumber
}

//Para funciones pequeñas mejora la legibilidad esta sintaxis:
fun substractCool(firstNumber: Int, secondNumber: Int)= firstNumber - secondNumber
