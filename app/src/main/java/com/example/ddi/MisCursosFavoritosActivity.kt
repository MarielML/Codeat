package com.example.ddi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ddi.data.Curso
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaClaro
import com.example.ddi.ui.theme.violetaOscuro

class MisCursosFavoritosActivity : ComponentActivity() {
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
                    TopBar(usuario)
                    Contenido(usuario)
                }
                Spacer(modifier = Modifier.height(10.dp))
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
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaOscuro),
                shape = RoundedCornerShape(0),
                onClick = { misCursos(usuario.nickname, usuario.password) }
            ) {
                Image(painterResource(id = R.drawable.baseline_folder_24), contentDescription = "")
            }
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaOscuro),
                shape = RoundedCornerShape(0),
                onClick = { }
            ) {
                Image(painterResource(id = R.drawable.baseline_star_24a), contentDescription = "")
            }
            Button(
                colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaOscuro),
                shape = RoundedCornerShape(0),
                onClick = { completos(usuario.nickname, usuario.password) }
            ) {
                Image(painterResource(id = R.drawable.baseline_add_task_24), contentDescription = "")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaOscuro),
                shape = RoundedCornerShape(0),
                modifier = Modifier.fillMaxHeight(),
                border = BorderStroke(1.dp, Color.White),
                onClick = { }
            ) {
                Image(painterResource(id = R.drawable.baseline_filter_alt_24), contentDescription = "")
            }
        }
    }

    @Composable
    private fun Contenido(usuario: Usuario) {
        Box(
            modifier = Modifier
                .height(480.dp)
                .padding(horizontal = 10.dp, vertical = 5.dp),
        ) {
            Column(
                Modifier
                    .padding(25.dp),
            ) {
                if(usuario.cursosCompletos.size == 0) {
                    TextCustom(text = "Aún no tienes cursos")
                } else {
                    TextCustom(text = "Favoritos")
                    MostrarCursos(usuario.cursosFavoritos, usuario)
                }
            }
        }
    }

    @Composable
    private fun MostrarCursos(datos: MutableList<Curso>, usuario: Usuario) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(datos) { item -> ListItemRow(item, usuario) }
        }
    }

    @Composable
    private fun ListItemRow(item: Curso, usuario: Usuario) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ){
            TextCustom(text = "0%", modifier = Modifier.border(1.dp, Color.White, CircleShape))
            Button(
                onClick = { curso(usuario.nickname, usuario.password, item.nombre) },
                colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaClaro),
                shape = RoundedCornerShape(10),
                modifier = Modifier
                    .width(180.dp)
                    .height(64.dp)
            ) {
                Text(
                    item.nombre,
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                Image(painterResource(id = R.drawable.baseline_star_24a), contentDescription = "", Modifier.width(50.dp))
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
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
                onClick = {  }
            ) {
                Image(painterResource(id = R.drawable.baseline_folder_24a), contentDescription = "")
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
                onClick = { perfil(usuario.nickname, usuario.password) }
            ) {
                Image(painterResource(id = R.drawable.baseline_person_24), contentDescription = "")
            }
        }
    }

    private fun curso(username: String, password: String, nombre: String) {
        val intent = Intent(this, CursoAgregadoActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
            putExtra("nombre", nombre)
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
    }

    private fun misCursos(username: String, password: String) {
        val intent = Intent(this, MisCursos::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }

    private fun completos(username: String, password: String) {
        val intent = Intent(this, MisCursosCompletosActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }
}
