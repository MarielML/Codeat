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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ddi.ui.theme.DDITheme

class RegistrarActivity : ComponentActivity() {
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
                    textFieldCustom(label = "Nombre de usuario", placeholder = "Nombre de usuario")
                    Spacer(modifier = Modifier.height(10.dp))
                    textFieldCustom(label = "Contraseña", placeholder = "Contraseña")
                    Spacer(modifier = Modifier.height(10.dp))
                    textFieldCustom(label = "Confirmar contraseña", placeholder = "Confirmar contraseña")
                    Spacer(modifier = Modifier.height(10.dp))
                    textFieldCustom(label = "Email", placeholder = "email@email.com")
                    Spacer(modifier = Modifier.height(30.dp))
                    ButtonCustom(text = "Registrarse", onClick = { registrarse() })
                }
            }
        }
    }

    private fun registrarse() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}