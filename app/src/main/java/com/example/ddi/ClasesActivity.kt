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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ddi.data.Curso
import com.example.ddi.data.CursoRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaOscuro

class ClasesActivity : ComponentActivity() {
    private val clases: MutableList<String> = mutableListOf("Clase 1", "Clase 2", "Clase 3", "Clase 4", "Clase 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val nombre: String? = bundle?.getString("nombre")
        val curso: Curso = CursoRepositorio.cursoElegido(nombre!!)

        setContent {
            Content(curso)
        }
    }

    @Composable
    private fun Content(curso: Curso) {
        CodeatTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = violetaOscuro
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    TopBar(curso)
                    Contenido()
                }
            }
        }
    }

    @Composable
    private fun TopBar(curso: Curso) {
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
        }
    }

    @Composable
    private fun Contenido() {
        Column(
            Modifier
                .fillMaxSize()
                .padding(25.dp),
        ) {
            MostrarClases(datos = clases)
        }
    }

    @Composable
    private fun MostrarClases(datos: MutableList<String>) {
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(datos) { item -> ListItemRow(item) }
        }
    }

    @Composable
    private fun ListItemRow(item: String) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(painterResource(id = R.drawable.baseline_add_task_24), contentDescription = "", modifier = Modifier.border(1.dp, Color.White, CircleShape))
            ButtonCustom(text = item, onClick = {
                clase1(item)
            })
        }
        Spacer(modifier = Modifier.height(5.dp))
    }

    private fun clase1(clase: String) {
        val intent = Intent(this, Clase1Activity::class.java).apply {
            putExtra("clase", clase)
        }
        startActivity(intent)
        onStop()
    }
}