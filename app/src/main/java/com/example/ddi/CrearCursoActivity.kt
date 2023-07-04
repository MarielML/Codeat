package com.example.ddi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ddi.data.Curso
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaClaro
import com.example.ddi.ui.theme.violetaOscuro

class CrearCursoActivity : ComponentActivity() {
    /*override fun onCreate(savedInstanceState: Bundle?) {
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
                    TopBar(usuario)
                    Contenido(usuario)
                }
                Menu(usuario)
            }
        }
    }

    @Composable
    private fun TopBar(usuario: Usuario) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .wrapContentHeight()
                .border(BorderStroke(1.dp, White))
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
        }
    }

    @Composable
    private fun Contenido(usuario: Usuario) {
        Column(
            Modifier
                .padding(25.dp)
        ) {
            TextCustom(text = "Cursos Pulicados")
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
            ) {
                MostrarCursosPublicados(usuario.cursosCreados, usuario)
            }
            TextCustom(text = "Cursos sin Pulicar")
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
            ) {
                MostrarCursosSinPublicar(usuario.cursosCreados, usuario)
            }
        }
    }

    @Composable
    private fun MostrarCursosPublicados(datos: MutableList<Curso>, usuario: Usuario) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
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
                    modifier = Modifier.background(violetaOscuro)
                        .border(1.dp, White),
                    contentColor = White
                ) {
                    Icon(Icons.Filled.Add,"")
                }
                ButtonCustom(text = item.nombre, onClick = {
                    curso(usuario.nickname, usuario.password, item.nombre)
                })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row {
                    Image(painterResource(id = R.drawable.baseline_favorite_24), contentDescription = "", Modifier.width(50.dp))
                    TextCustom(text = item.favorito.toString())
                }
                Row {
                    Image(painterResource(id = R.drawable.baseline_comment_24), contentDescription = "", Modifier.width(50.dp))
                    TextCustom(text = item.usuarios.toString())
                }
            }
        }
    }

    @Composable
    private fun MostrarCursosSinPublicar(datos: MutableList<Curso>, usuario: Usuario) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(datos) { item -> ListItemRowSinPublicar(item, usuario) }
        }
    }

    @Composable
    private fun ListItemRowSinPublicar(item: Curso, usuario: Usuario) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ){
            ButtonCustom(text = item.nombre, onClick = {
                cursoCreado(usuario.nickname, usuario.password, item.nombre)
            })
        }
        Spacer(modifier = Modifier.height(5.dp))
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

    private fun cursoCreado(username: String, password: String, nombre: String) {
        val intent = Intent(this, CursoCreadoActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
            putExtra("nombre", nombre)
        }
        startActivity(intent)
        onStop()
    }

    private fun crear(username: String, password: String) {
        val intent = Intent(this, CrearActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }

    @Composable
    private fun Menu(usuario: Usuario) {
        Row(modifier = Modifier
            .size(30.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaClaro),
                shape = RoundedCornerShape(0),
                onClick = { misCursos(usuario.nickname, usuario.password) }
            ) {
                Image(painterResource(id = R.drawable.baseline_folder_24), contentDescription = "")
            }
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaClaro),
                shape = RoundedCornerShape(0),
                onClick = { descubrir(usuario.nickname, usuario.password) }
            ) {
                Image(painterResource(id = R.drawable.baseline_search_24), contentDescription = "")
            }
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaClaro),
                shape = RoundedCornerShape(0),
                border = BorderStroke(1.dp, color = White),
                onClick = { }
            ) {
                Image(painterResource(id = R.drawable.baseline_add_24), contentDescription = "")
            }
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaClaro),
                shape = RoundedCornerShape(0),
                onClick = { perfil(usuario.nickname, usuario.password) }
            ) {
                Image(painterResource(id = R.drawable.baseline_person_100), contentDescription = "")
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

    private fun perfil(username: String, password: String) {
        val intent = Intent(this, PerfilActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }*/
}