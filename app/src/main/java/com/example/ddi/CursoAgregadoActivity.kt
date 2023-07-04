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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ddi.data.Curso
import com.example.ddi.data.CursoRepositorio
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaOscuro

class CursoAgregadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val nombre: String? = bundle?.getString("nombre")
        val curso: Curso = CursoRepositorio.cursoElegido(nombre!!)
        val username: String? = bundle.getString("username")
        val password: String? = bundle.getString("password")
        val usuario: Usuario = UsuarioRepositorio.iniciar(username!!, password!!)

        setContent {
            Content(usuario, curso)
        }
    }

    @Composable
    private fun Content(usuario: Usuario, curso: Curso) {
        CodeatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = violetaOscuro
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    TopBar(usuario, curso)
                    Contenido(curso)
                }
                Iniciar(usuario, curso)
            }
        }
    }

    @Composable
    private fun TopBar(usuario: Usuario, curso: Curso) {
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
            TextCustom(text = curso.nombre, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.baseline_star_24a),
                contentDescription = "",
                modifier = Modifier
                    .clickable(enabled = true, onClick = {
                        usuario.cursosFavoritos.add(curso)
                    })
            )
        }
    }

    @Composable
    private fun Contenido(curso: Curso) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(25.dp),
        ) {
            TextCustom(text = "Descripción:")
            TextCustom(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
                    "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut" +
                    " aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                    "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia" +
                    " deserunt mollit anim id est laborum.", fontSize = 16.sp, modifier = Modifier.border(
                BorderStroke(1.dp, Color.White)
            ))
            TextCustom(text = "Horas: ${curso.horas}")
            TextCustom(text = "Creador: ${curso.creador.nickname}")
        }
    }

    @Composable
    private fun Iniciar(usuario: Usuario, curso: Curso) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(10.dp)
        ) {
            ButtonCustom(text = "Iniciar", onClick = { iniciar(usuario.nickname, usuario.password, curso.nombre) })
        }
    }

    private fun iniciar(username: String, password: String, nombre: String) {
        val intent = Intent(this, ClasesActivity::class.java).apply {
            putExtra("nombre", nombre)
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }
}