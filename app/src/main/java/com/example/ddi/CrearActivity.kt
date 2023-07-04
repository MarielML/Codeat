package com.example.ddi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ddi.data.Curso
import com.example.ddi.data.CursoRepositorio
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaOscuro

class CrearActivity : ComponentActivity() {

    private lateinit var nuevoCurso: Curso
    var nombre: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val username: String? = bundle?.getString("username")
        val password: String? = bundle?.getString("password")
        val usuario: Usuario = UsuarioRepositorio.iniciar(username!!, password!!)

        setContent {
            Content(usuario)
        }
    }

    @Composable
    private fun Content(usuario: Usuario) {
        CodeatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = violetaOscuro
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    TopBar()
                    Contenido(usuario)
                }
            }
        }
    }

    @Composable
    private fun TopBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_close_24),
                contentDescription = "",
                modifier = Modifier
                    .clickable(enabled = true, onClick = {
                        finish()
                    })
            )
            TextCustom(text = "Nuevo Curso")
        }
    }

    @Composable
    private fun Contenido(usuario: Usuario) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(25.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Row {
                TextCustom(text = "Nombre:")
                nombre = textFieldCustom(label = "", placeholder = "")
            }
            Row {
                TextCustom(text = "Tags:")
                ButtonCustom(text = "Web", onClick = { }, width = 50.dp, height = 20.dp, fontSize = 16)
                ButtonCustom(text = "Android", onClick = { }, width = 50.dp, height = 20.dp, fontSize = 16)
                ButtonCustom(text = "iOS", onClick = { }, width = 50.dp, height = 20.dp, fontSize = 16)
                ButtonCustom(text = "Java", onClick = { }, width = 50.dp, height = 20.dp, fontSize = 16)
                ButtonCustom(text = "+", onClick = { }, width = 10.dp, height = 20.dp, fontSize = 16)
            }
            TextCustom(text = "Descripción:")
            TextFieldCustomDescripcion(label = "", placeholder = "")
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FloatingActionButton (
                    onClick = {
                        if(!CursoRepositorio.existe(nombre) && !usuario.creado(nombre)) {
                            nuevoCurso = Curso(nombre = nombre, creador = usuario)
                            usuario.crearCurso(nuevoCurso)
                            crearCurso(usuario.nickname, usuario.password)
                            finish()
                        }
                    },
                    modifier = Modifier
                        .border(1.dp, Color.White, CircleShape),
                    shape = CircleShape,
                    containerColor = violetaOscuro,
                    contentColor = Color.White,
                ) {
                    Icon(Icons.Filled.Add,"")
                }
            }

        }
    }

    private fun crearCurso(username: String, password: String) {
        val intent = Intent(this, PerfilActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
    }
}