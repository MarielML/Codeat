package com.example.ddi.data

class Usuario (
    var nickname: String= "",
    var password:String = "",
    var email: String = "",
    val cursos: MutableList<Curso> = mutableListOf(),
    val cursosPublicados: MutableList<Curso> = mutableListOf(),
    val cursosSinPublicar: MutableList<Curso> = mutableListOf(),
    val seguidores: MutableList<String> = mutableListOf(),
    val seguidos: MutableList<String> = mutableListOf()
) {
    fun agregarCurso(curso: Curso) {
        if (!existe(curso.nombre)) {
            cursos.add(curso)
        }
    }

    fun existe(nombre: String): Boolean {
        return (cursos.any { curso: Curso -> curso.nombre == nombre})
    }

    fun publicarCurso(curso: Curso) {
        if (!publicado(curso.nombre)) {
            cursosPublicados.add(curso)
            cursosSinPublicar.remove(curso)
        }
    }

    fun crearCurso(curso: Curso) {
        if (!creado(curso.nombre)) {
            cursosSinPublicar.add(curso)
        }
    }

    fun publicado(nombre: String): Boolean {
        return (cursosPublicados.any { curso: Curso -> curso.nombre == nombre})
    }

    fun creado(nombre: String): Boolean {
        return (cursosSinPublicar.any { curso: Curso -> curso.nombre == nombre})
    }

    fun cursoElegido(nombre: String): Curso {
        var cursoElegido = Curso()
        for (elemento in cursosSinPublicar) {
            if (elemento.nombre == nombre) {
                cursoElegido = elemento
            }
        }
        return cursoElegido
    }

    fun agregarSeguidor(seguidor: String) {
        if (!existeSeguidor(seguidor)) {
            seguidores.add(seguidor)
        }
    }

    fun existeSeguidor(nickname: String): Boolean {
        return (seguidores.any { seguidor: String -> seguidor == nickname})
    }

    fun agregarSeguido(seguido: String) {
        if (!existeSeguido(seguido)) {
            seguidos.add(seguido)
        }
    }

    fun existeSeguido(nickname: String): Boolean {
        return (seguidos.any { seguido: String -> seguido == nickname})
    }
}