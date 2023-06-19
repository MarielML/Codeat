package com.example.ddi.data

data class Usuario (
    var nickname: String= "",
    var password:String = "",
    var email: String = "",
    val cursos: MutableList<Curso> = mutableListOf()
)