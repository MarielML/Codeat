package com.example.ddi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.DDITheme

class RegistrarActivity : ComponentActivity() {

    private lateinit var nuevoUsuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    private fun Content() {
        DDITheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    Modifier.fillMaxSize()
                        .padding(25.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    TextCustom(text = "Crear cuenta")
                    Spacer(modifier = Modifier.height(20.dp))
                    val nombre = textFieldCustom(label = "Nombre de usuario", placeholder = "Nombre de usuario")
                    Spacer(modifier = Modifier.height(10.dp))
                    val contrasenia = textFieldPasswordCustom(label = "Contraseña", placeholder = "Contraseña")
                    Spacer(modifier = Modifier.height(10.dp))
                    val confirmar = textFieldPasswordCustom(label = "Confirmar contraseña", placeholder = "Confirmar contraseña")
                    Spacer(modifier = Modifier.height(10.dp))
                    val email = textFieldEmailCustom(label = "Email", placeholder = "email@email.com")
                    Spacer(modifier = Modifier.height(30.dp))
                    ButtonCustom(text = "Registrarse", onClick = {
                        if(validar(nombre, contrasenia, confirmar, email)) {
                            if(contrasenia == confirmar) {
                                nuevoUsuario = Usuario(nombre, contrasenia, email)
                                UsuarioRepositorio.agregar(nuevoUsuario)
                                finish()
                            }
                        }
                    })
                }
            }
        }
    }

    private fun validar(nombre: String, contrasenia: String, confirmar: String, email: String): Boolean {
        return nombre != "" && contrasenia != "" && confirmar != "" && email != "" && email.contains("@")
    }
}