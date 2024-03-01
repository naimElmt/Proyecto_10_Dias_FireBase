package com.example.firebase_10pelis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.firebase_10pelis.data.DataSource
import com.example.firebase_10pelis.model.Pelicula
import com.example.firebase_10pelis.ui.theme.Firebase_10PelisTheme
import com.example.firebase_10pelis.ui.theme.changeIconColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Firebase_10PelisTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    FilmApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmApp() {
    var listaPeliculas by remember { mutableStateOf(listOf<Pelicula>()) }

    Scaffold(
        topBar = {
            FilmTopAppBar()
        },
        content = {
            LazyColumn(contentPadding = it) {
                DataSource().getPeliculas { listaPeliculas = it }
                items(listaPeliculas) {
                    FilmItem(
                        pelicula = it,
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                    )
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmTopAppBar(modifier: Modifier = Modifier) {
    val icon = changeIconColor()
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.icon_size)),
                    painter = icon,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.spacer_rndm)))
                Box(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displayLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Firebase_10PelisTheme {
        FilmApp()
    }
}