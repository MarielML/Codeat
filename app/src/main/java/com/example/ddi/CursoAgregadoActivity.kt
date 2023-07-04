package com.example.ddi

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ddi.data.Curso
import com.example.ddi.data.CursoRepositorio
import com.example.ddi.ui.theme.CodeatTheme
import com.example.ddi.ui.theme.violetaOscuro

class CursoAgregadoActivity : ComponentActivity() {
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
                    Contenido(curso)
                }
                Iniciar(curso)
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
            TextCustom(text = curso.nombre, textAlign = TextAlign.Center)
        }
    }

    @Composable
    private fun Contenido(curso: Curso) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(25.dp),
        ) {
            TextCustom(text = curso.descripcion)
            TextCustom(text = "Horas: ${curso.horas}")
            TextCustom(text = "Creador: ${curso.creador.nickname}")
        }
    }

    @Composable
    private fun Iniciar(curso: Curso) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(10.dp)
        ) {
            ButtonCustom(text = "Iniciar", onClick = { iniciar(curso.nombre) })
        }
    }

    private fun iniciar(nombre: String) {
        val intent = Intent(this, ClasesActivity::class.java).apply {
            putExtra("nombre", nombre)
        }
        startActivity(intent)
        onStop()
    }
}