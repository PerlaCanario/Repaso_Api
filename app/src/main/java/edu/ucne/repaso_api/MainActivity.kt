package edu.ucne.repaso_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.repaso_api.remote.dto.VerboDto
import edu.ucne.repaso_api.ui.theme.Repaso_ApiTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Repaso_ApiTheme {
                verbosScreen()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    verbosScreen()
                }
            }
        }
    }
}

@Composable
fun verbosScreen(viewModel: VerboViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    LazyColumn {
        items(uiState.value.verbos) { verbo ->
            verbosCard(verbo = verbo)
        }
    }
}

@Composable
fun verbosCard(
    modifier: Modifier = Modifier,
    verbo: VerboDto
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .height(300.dp)
        .padding(vertical = 16.dp, horizontal = 14.dp), shape = RoundedCornerShape(20.dp),
    ) {
        Column(modifier.padding(16.dp)) {

            Spacer(modifier = modifier.padding(4.dp))
            Text(text = "Verbo: " + verbo.Verbo)
            Spacer(modifier = modifier.padding(4.dp))
            Text(text = "Nivel: " + verbo.Nivel)
            Spacer(modifier = modifier.padding(4.dp))
            Text(text = "Categoria: " + verbo.Categoria)
            Spacer(modifier = modifier.padding(4.dp))
            AsyncImage(
                model = verbo.Imagen,
                contentDescription = null,
                modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun preview() {
    verbosCard(verbo = VerboDto(
        "anotar",
        "Memorizar",
        "Nivel 1",
        "https://cdn.pixabay.com/photo/2020/04/12/07/18/post-it-5033079__480.jpg"
    ))
}