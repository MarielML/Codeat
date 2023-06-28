package com.example.ddi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme

class MainActivity : ComponentActivity() {
    private lateinit var usuario: Usuario
    private var error = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //Logo()
            Content()
        }
    }

    @Composable
    private fun Content() {
        CodeatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
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
                    TextCustom(text = error, color = Color.Red, fontSize = 15.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    ButtonCustom(text = "ok", onClick = {
                        if(validar(nombre, contrasenia)) {
                            if(!UsuarioRepositorio.existe(nombre, contrasenia)) {
                                error = "Usuario y/o contraseña incorrectos"
                            } else {
                                error = ""
                                usuario = UsuarioRepositorio.iniciar(nombre, contrasenia)
                                descubrir(nombre, contrasenia)
                                finish()
                            }
                        } else {
                            error = "Debes completar todos los campos"
                        }
                    }, width = 80.dp)
                    Spacer(modifier = Modifier.height(20.dp))
                    TextCustom(text = "¿No tiene una cuenta?")
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

    /*@Composable
    fun Logo() {
        val infiniteTransition = rememberInfiniteTransition()

        val alpha by infiniteTransition.animateFloat(
            initialValue = 1F,
            targetValue = 0F,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    5000.0.toFloat() / 2 at 0
                    5000.0.toFloat() / 2 at 1
                },
                repeatMode = RepeatMode.Restart
            )
        )
        Column(
            modifier = Modifier.background(Color.Red).fillMaxSize().alpha(alpha),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_settings_24),
                contentDescription = ""
            )
        }
    }*/
}
