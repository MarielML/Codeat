package com.example.ddi.data

data class Curso (
    var nombre: String = "",
    var creador: Usuario = Usuario(),
    var puntaje: Double = 0.0
)