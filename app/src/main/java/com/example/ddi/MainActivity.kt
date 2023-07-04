package com.example.ddi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaOscuro

class MainActivity : ComponentActivity() {
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    @Composable
    private fun Content() {
        CodeatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = violetaOscuro
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(25.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    TextCustom(text = "Iniciar sesión")
                    Spacer(modifier = Modifier.height(20.dp))
                    val nombre = textFieldCustom(label = "Usuario", placeholder = "Usuario...")
                    Spacer(modifier = Modifier.height(10.dp))
                    val contrasenia = textFieldPasswordCustom(label = "Contraseña", placeholder = "Contraseña...")
                    Spacer(modifier = Modifier.height(5.dp))
                    ButtonCustom(text = "continuar", onClick = {
                        if(validar(nombre, contrasenia)) {
                            if(UsuarioRepositorio.existe(nombre, contrasenia)) {
                                usuario = UsuarioRepositorio.iniciar(nombre, contrasenia)
                                descubrir(nombre, contrasenia)
                                finish()
                            }
                        }
                    })
                    Spacer(modifier = Modifier.height(20.dp))
                    TextCustom(text = "¿No tienes cuenta?")
                    Spacer(modifier = Modifier.height(10.dp))
                    ButtonCustom(text = "Registrarse", onClick = { registrarse() })
                }
            }
        }
    }

    private fun registrarse() {
        val intent = Intent(this, RegistrarActivity::class.java)
        startActivity(intent)
    }

    private fun descubrir(username: String, password: String) {
        val intent = Intent(this, DescubrirActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
    }

    private fun validar(nombre: String, contrasenia: String): Boolean {
        return nombre != "" && contrasenia != ""
    }
}
