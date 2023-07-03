package com.example.ddi.data

data class Curso (
    var nombre: String = "",
    var creador: Usuario = Usuario(),
    var descripcion: String = "",
    var horas: Double = 5.0,
    var favorito: Int = 0,
    var usuarios: Int = 0
)