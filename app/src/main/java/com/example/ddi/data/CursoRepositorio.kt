package com.example.ddi.data

object CursoRepositorio {
    val cursos = mutableListOf<Curso>()

    init {
        cursos.add(Curso("JavaScript I", "",4.8))
        cursos.add(Curso("JavaScript II", "",4.9))
        cursos.add(Curso("TypeScript I", "",4.9))
        cursos.add(Curso("TypeScript II", "",4.7))
        cursos.add(Curso("Phyton I", "Pepe Argento",4.8))
    }

    fun agregar(curso: Curso) {
        if (!existe(curso.nombre)) {
            cursos.add(curso)
        }
    }

    fun existe(nombre: String): Boolean {
        return (cursos.any { curso: Curso -> curso.nombre == nombre})
    }

    fun cursoElegido(nombre: String): Curso {
        var cursoElegido = Curso()
        for (elemento in cursos) {
            if (elemento.nombre == nombre) {
                cursoElegido = elemento
            }
        }
        return cursoElegido
    }
}