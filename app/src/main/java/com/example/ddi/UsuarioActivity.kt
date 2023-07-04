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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ddi.data.Curso
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaOscuro

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
                color = violetaOscuro
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
            TextCustom(text = creador, textAlign = TextAlign.Center)
        }
    }

    @Composable
    private fun Contenido(usuario: Usuario, creador: String) {
        Column(
            Modifier
                .padding(25.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painterResource(id = R.drawable.baseline_person_200),
                    contentDescription = "",
                    Modifier
                        .width(50.dp)
                        .border(BorderStroke(1.dp, White))
                )
                Column {
                    TextCustom(text = creador)
                    Row {
                        if(usuario.nickname != creador) {
                            Button(colors = ButtonDefaults.elevatedButtonColors(containerColor = White),
                                shape = RoundedCornerShape(0),
                                border = BorderStroke(1.dp, Black),
                                onClick = {
                                    usuario.agregarSeguido(UsuarioRepositorio.creador(creador))
                                    UsuarioRepositorio.creador(creador).agregarSeguidor(usuario)
                                    usuario(usuario.nickname, usuario.password, creador)
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
            Row {
                Column {
                    TextCustom(text = "${UsuarioRepositorio.creador(creador).seguidores.size} seguidores", fontSize = 16.sp)
                    TextCustom(text = "${UsuarioRepositorio.creador(creador).seguidos.size} seguidos", fontSize = 16.sp)
                }
                Row {
                    Image(painterResource(id = R.drawable.baseline_comment_24), contentDescription = "", Modifier.width(50.dp))
                    TextCustom(text = UsuarioRepositorio.creador(creador).comentarios.toString())
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            TextCustom(text = "Cursos Pulicados")
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
            ) {
                MostrarCursosPublicados(UsuarioRepositorio.creador(creador).cursosCreados, usuario)
            }
        }
    }

    @Composable
    private fun MostrarCursosPublicados(datos: MutableList<Curso>, usuario: Usuario) {
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(datos) { item -> ListItemRow(item, usuario) }
        }
    }

    @Composable
    private fun ListItemRow(item: Curso, usuario: Usuario) {
        Column {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                FloatingActionButton (
                    onClick = {
                        usuario.agregarCurso(item)
                        misCursos(usuario.nickname, usuario.password)
                        finish()
                    },
                    modifier = Modifier
                        .border(1.dp, White, CircleShape),
                    shape = CircleShape,
                    containerColor = violetaOscuro,
                    contentColor = White,
                ) {
                    Icon(Icons.Filled.Add,"")
                }
                ButtonCustom(text = item.nombre, onClick = {
                    curso(usuario.nickname, usuario.password, item.nombre)
                })
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
    }

    private fun misCursos(username: String, password: String) {
        val intent = Intent(this, MisCursos::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }

    private fun curso(username: String, password: String, nombre: String) {
        val intent = Intent(this, CursoActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
            putExtra("nombre", nombre)
        }
        startActivity(intent)
        onStop()
    }

    private fun usuario(username: String, password: String, creador: String) {
        val intent = Intent(this, UsuarioActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
            putExtra("creador", creador)
        }
        startActivity(intent)
        finish()
    }
}