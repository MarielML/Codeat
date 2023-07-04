package com.example.ddi.data

object CursoRepositorio {
    val cursos = mutableListOf<Curso>()
    private val tendencia = mutableListOf<Curso>()
    private val favoritos = mutableListOf<Curso>()
    private val masUsados = mutableListOf<Curso>()

    init {
        cursos.add(Curso("JavaScript I", Usuario(nickname = "Anónimo2"), favorito = 4599, usuarios = 5620))
        cursos.add(Curso("JavaScript II", Usuario(nickname = "Anónimo2"), favorito = 5, usuarios = 10))
        cursos.add(Curso("Java I", Usuario(nickname = "Anónimo2"), favorito = 4213, usuarios = 7620))
        cursos.add(Curso("TypeScript I", Usuario(nickname = "Anónimo"), favorito = 5, usuarios = 10))
        cursos.add(Curso("TypeScript II", Usuario(nickname = "Anónimo2"),favorito = 5, usuarios = 10))
        cursos.add(Curso("Python I", Usuario(nickname = "Pepe Argento"), favorito = 3123, usuarios = 4045))
        cursos.add(Curso("React I", Usuario(nickname = "A"), favorito = 5, usuarios = 10))
        cursos.add(Curso("Jetpack Compose", Usuario(nickname = "Anónimo2"), favorito = 122, usuarios = 543))
        cursos.add(Curso("Kotlin I", Usuario(nickname = "Anónimo2"),favorito = 97, usuarios = 421))
        cursos.add(Curso("Koin", Usuario(nickname = "Anónimo2"), favorito = 78, usuarios = 210))
        cursos.add(Curso("React II", Usuario(nickname = "A")))
        favoritos.add(Curso("JavaScript I", Usuario(nickname = "Anónimo"), favorito = 4599, usuarios = 5620))
        favoritos.add(Curso("Java I", Usuario(nickname = "Anónimo2"), favorito = 4213, usuarios = 7620))
        favoritos.add(Curso("Python I", Usuario(nickname = "Pepe Argento"), favorito = 3123, usuarios = 4045))
        masUsados.add(Curso("Java I", Usuario(nickname = "Anónimo2"), favorito = 4213, usuarios = 7620))
        masUsados.add(Curso("JavaScript I", Usuario(nickname = "Anónimo"), favorito = 4599, usuarios = 5620))
        masUsados.add(Curso("Python I", Usuario(nickname = "Pepe Argento"), favorito = 3123, usuarios = 4045))
        tendencia.add(Curso("Jetpack Compose", Usuario(nickname = "Anónimo2"), favorito = 122, usuarios = 543))
        tendencia.add(Curso("Kotlin I", Usuario(nickname = "Anónimo2"),favorito = 97, usuarios = 421))
        tendencia.add(Curso("Koin", Usuario(nickname = "Anónimo2"), favorito = 78, usuarios = 210))
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

    fun ordenarCursos(): List<Curso> {
        return cursos.sortedByDescending { it.favorito }
    }

    fun ordenarTendencia(): List<Curso> {
        return tendencia.sortedByDescending { it.favorito }
    }

    fun ordenarFavoritos(): List<Curso> {
        return favoritos.sortedByDescending { it.favorito }
    }

    fun ordenarUsados(): List<Curso> {
        return masUsados.sortedByDescending { it.usuarios }
    }
}