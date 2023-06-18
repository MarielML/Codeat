package com.example.ddi.data

object UsuarioRepositorio {
    val usuarios = mutableListOf<Usuario>()

    init {
        usuarios.add(Usuario("Mariel", "aaaa", "a@email"))
    }

    fun agregar(usuario: Usuario) {
        if (!existe(usuario.nickname, usuario.password)) {
            usuarios.add(usuario)
        }
    }

    fun existe(nickname: String, password: String): Boolean {
        return (usuarios.any { usuario: Usuario -> usuario.nickname == nickname && usuario.password == password })
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