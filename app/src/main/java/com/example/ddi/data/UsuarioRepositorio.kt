package com.example.ddi.data

object UsuarioRepositorio {
    val usuarios = mutableListOf<Usuario>()

    init {
        usuarios.add(Usuario("Usuario", "A", "a@email",
            cursos =
        mutableListOf(Curso("JavaScript I", "Anónimo",4.8),
                      Curso("JavaScript II", "Anónimo",4.9)),
            cursosPublicados =
        mutableListOf(Curso("React I", "Usuario",3.0),
                      Curso("React II", "Usuario",3.5))))
    }

    fun agregar(usuario: Usuario) {
        if (!existeNombreOEmail(usuario.nickname, usuario.email)) {
            usuarios.add(usuario)
        }
    }

    fun existe(nickname: String, password: String): Boolean {
        return (usuarios.any { usuario: Usuario -> usuario.nickname == nickname && usuario.password == password })
    }

    fun existeNombreOEmail(nickname: String, email: String): Boolean {
        return (usuarios.any { usuario: Usuario -> usuario.nickname == nickname || usuario.email == email })
    }

    fun iniciar(nickname: String, password: String): Usuario {
        var usuarioIniciado = Usuario()
        for (elemento in usuarios) {
            if (elemento.nickname == nickname && elemento.password == password) {
                usuarioIniciado = elemento
            }
        }
        return usuarioIniciado
    }
}