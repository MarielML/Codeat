package com.example.ddi.data

class Usuario (
    var nickname: String= "",
    var password:String = "",
    var email: String = "",
    var comentarios: Int = 0,
    val cursos: MutableList<Curso> = mutableListOf(),
    val cursosFavoritos: MutableList<Curso> = mutableListOf(),
    val cursosCompletos: MutableList<Curso> = mutableListOf(),
    val cursosCreados: MutableList<Curso> = mutableListOf(),
    val seguidores: MutableList<Usuario> = mutableListOf(),
    val seguidos: MutableList<Usuario> = mutableListOf()
) {
    fun agregarCurso(curso: Curso) {
        if (!existe(curso.nombre)) {
            cursos.add(curso)
        }
    }

    private fun existe(nombre: String): Boolean {
        return (cursos.any { curso: Curso -> curso.nombre == nombre})
    }

    fun crearCurso(curso: Curso) {
        if (!creado(curso.nombre)) {
            cursosCreados.add(curso)
            CursoRepositorio.cursos.add(curso)
        }
    }

    fun creado(nombre: String): Boolean {
        return (CursoRepositorio.cursos.any { curso: Curso -> curso.nombre == nombre})
    }

    fun agregarSeguidor(seguidor: Usuario) {
        if (!existeSeguidor(seguidor.nickname)) {
            seguidores.add(seguidor)
        }
    }

    private fun existeSeguidor(nickname: String): Boolean {
        return (seguidores.any { usuario: Usuario -> usuario.nickname == nickname })
    }

    fun agregarSeguido(seguido: Usuario) {
        if (!existeSeguido(seguido.nickname)) {
            seguidos.add(seguido)
        }
    }

    fun existeSeguido(nickname: String): Boolean {
        return (seguidos.any { usuario: Usuario -> usuario.nickname == nickname })
    }
}