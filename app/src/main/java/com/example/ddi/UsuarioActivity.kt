package com.example.ddi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme

class UsuarioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val username: String? = bundle?.getString("username")
        val password: String? = bundle?.getString("password")
        val usuario: Usuario = UsuarioRepositorio.iniciar(username!!, password!!)
        val creador: String? = bundle.getString("creador")

        setContent {
            Content(usuario, creador!!)
        }
    }

    @Composable
    private fun Content(usuario: Usuario, creador: String) {
        CodeatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    TopBar(creador)
                    Contenido(usuario, creador)
                }
            }
        }
    }

    @Composable
    private fun TopBar(creador: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .wrapContentHeight()
                .border(BorderStroke(1.dp, Black))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextCustom(text = creador, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.baseline_settings_24),
                contentDescription = "",
                modifier = Modifier
                    .clickable(enabled = true, onClick = {
                        configuracion()
                    })
            )
        }
    }

    @Composable
    private fun Contenido(usuario: Usuario, creador: String) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ){
            Image(painterResource(id = R.drawable.baseline_person_100), contentDescription = "", Modifier.width(50.dp))
            Column {
                TextCustom(text = creador)
                if(usuario.nickname != creador) {
                    Button(colors = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(0),
                        border = BorderStroke(1.dp, Black),
                        onClick = {
                            usuario.agregarSeguido(UsuarioRepositorio.creador(creador))
                            UsuarioRepositorio.creador(creador).agregarSeguidor(usuario)
                        }
                    ) {
                        if (!usuario.existeSeguido(creador)) {
                            Text("Seguir", color = Black)
                        } else {
                            Text("Siguiendo", color = Black)
                        }
                    }
                }
            }
        }
    }

    private fun configuracion() {
        val intent = Intent(this, ConfiguracionActivity::class.java)
        startActivity(intent)
        onStop()
    }
}