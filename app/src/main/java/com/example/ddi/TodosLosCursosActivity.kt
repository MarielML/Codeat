package com.example.ddi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.ddi.data.Curso
import com.example.ddi.data.CursoRepositorio
import com.example.ddi.data.Usuario
import com.example.ddi.data.UsuarioRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaClaro
import com.example.ddi.ui.theme.violetaOscuro

class TodosLosCursosActivity : ComponentActivity() {
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
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painterResource(id = R.drawable.baseline_search_24), contentDescription = "")
            Buscador()
            Spacer(modifier = Modifier.weight(1f))
            Button(colors = ButtonDefaults.elevatedButtonColors(containerColor = violetaOscuro),
                shape = RoundedCornerShape(0),
                border = BorderStroke(1.dp, White),
                modifier = Modifier.fillMaxHeight(),
                onClick = { }
            ) {
                Image(painterResource(id = R.drawable.baseline_filter_alt_24), contentDescription = "")
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Buscador() {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text(text = "", color = White) },
            placeholder = {
                Text(
                    text = "Buscar",
                    fontWeight = FontWeight.Light,
                    color = White
                )
            },
            modifier = Modifier.width(150.dp)
        )
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
                    .padding(25.dp)
            ) {
                MostrarCursos(CursoRepositorio.ordenarCursos() as MutableList<Curso>, usuario)
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
                border = BorderStroke(1.dp, color = White),
                onClick = { }
            ) {
                Image(painterResource(id = R.drawable.baseline_search_24a), contentDescription = "")
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
        val intent = Intent(this, CursoActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
            putExtra("nombre", nombre)
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

    private fun perfil(username: String, password: String) {
        val intent = Intent(this, PerfilActivity::class.java).apply {
            putExtra("username", username)
            putExtra("password", password)
        }
        startActivity(intent)
        onStop()
    }
}