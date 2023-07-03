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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaOscuro

class PerfilActivity : ComponentActivity() {
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
                Menu(usuario)
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
                .border(BorderStroke(1.dp, Color.Black))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextCustom(text = "Perfil")
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
    private fun Contenido(usuario: Usuario) {
        Column (
            Modifier
                .padding(25.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painterResource(id = R.drawable.baseline_person_100),
                    contentDescription = "",
                    Modifier.width(50.dp)
                )
                TextCustom(text = usuario.nickname)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row {
                    Image(painterResource(id = R.drawable.baseline_favorite_24), contentDescription = "", Modifier.width(50.dp))
                    TextCustom(text = usuario.meGusta.toString())
                }
                Spacer(modifier = Modifier.width(5.dp))
                Row {
                    Image(painterResource(id = R.drawable.baseline_comment_24), contentDescription = "", Modifier.width(50.dp))
                    TextCustom(text = usuario.comentarios.toString())
                }
            }
            Row {
                Box(
                    modifier = Modifier
                        .height(140.dp)
                ) {
                    Column {
                        TextCustom(text = "Seguidos: ${usuario.seguidos.size}", fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        MostrarSeguidos(usuario.seguidos)
                    }
                }
                Spacer(modifier = Modifier.width(20.dp))
                Box(
                    modifier = Modifier
                        .height(140.dp)
                ) {
                    Column {
                        TextCustom(text = "Seguidores: ${usuario.seguidores.size}", fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        MostrarSeguidores(usuario.seguidores)
                    }
                }
            }
        }
    }

    @Composable
    private fun MostrarSeguidores(datos: MutableList<Usuario>) {
        LazyColumn {
            items(datos) { item -> ListItemRowSeguidores(item) }
        }
    }

    @Composable
    private fun ListItemRowSeguidores(item: Usuario) {
        Row {
            TextCustom(text = item.nickname, fontSize = 20.sp)
        }
    }

    @Composable
    private fun MostrarSeguidos(datos: MutableList<Usuario>) {
        LazyColumn {
            items(datos) { item -> ListItemRowSeguidos(item) }
        }
    }

    @Composable
    private fun ListItemRowSeguidos(item: Usuario) {
        Row {
            TextCustom(text = item.nickname, fontSize = 20.sp)
        }
    }


    @Composable
    private fun Menu(usuario: Usuario) {
        Row(modifier = Modifier
            .size(30.dp)
            .border(BorderStroke(1.dp, Color.Black)),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = White),
                shape = RoundedCornerShape(0),
                onClick = { misCursos(usuario.nickname, usuario.password) }
            ) {
                Image(painterResource(id = R.drawable.baseline_folder_24), contentDescription = "")
            }
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = White),
                shape = RoundedCornerShape(0),
                onClick = { descubrir(usuario.nickname, usuario.password) }
            ) {
                Image(painterResource(id = R.drawable.baseline_search_24), contentDescription = "")
            }
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = White),
                shape = RoundedCornerShape(0),
                onClick = { crear(usuario.nickname, usuario.password) }
            ) {
                Image(painterResource(id = R.drawable.baseline_add_24), contentDescription = "")
            }
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = Gray),
                shape = RoundedCornerShape(0),
                onClick = { }
            ) {
                Image(painterResource(id = R.drawable.baseline_person_24), contentDescription = "")
            }
        }
    }

    private fun misCursos(username: String, password: String) {
        val intent = Intent(this, MisCursos::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }

    private fun descubrir(username: String, password: String) {
        val intent = Intent(this, DescubrirActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }

    private fun crear(username: String, password: String) {
        val intent = Intent(this, CrearCursoActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }

    private fun configuracion() {
        val intent = Intent(this, ConfiguracionActivity::class.java)
        startActivity(intent)
        onStop()
    }
}