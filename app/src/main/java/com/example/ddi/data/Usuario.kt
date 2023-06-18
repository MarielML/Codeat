package com.example.ddi.data

class Usuario (
    var nickname: String= "",
    var password:String = "",
    var email: String = "",
    val cursos: MutableList<Curso> = mutableListOf<Curso>()
) {
    init {
        cursos.add(Curso("JavaScript I", "",4.8))
        cursos.add(Curso("JavaScript II", "",4.9))
    }
}