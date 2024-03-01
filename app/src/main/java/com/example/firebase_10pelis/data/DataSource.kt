package com.example.firebase_10pelis.data

import android.util.Log
import com.example.firebase_10pelis.model.Pelicula
import com.google.firebase.firestore.FirebaseFirestore

class DataSource {

    private val TAG = "DataSource"
    init {
        Log.d(TAG, "DataSource class instantiated")
    }

    private val COLECCION_PELICULAS = "peliculas"
    private val CAMPO_DIA = "dia"
    private val CAMPO_TITULO = "titulo"
    private val CAMPO_DESCRIPCION = "descripcion"
    private val CAMPO_IMAGEN = "imagen"

    private val db = FirebaseFirestore.getInstance()

    private val peliculas = db.collection(COLECCION_PELICULAS)

    init {
        Log.d(TAG, "lee coleccion")
    }
    fun getPeliculas(setLista: (List<Pelicula>) -> Unit) {
            Log.d(TAG, "Entra al metodo")

        val listaPeliculas = mutableListOf<Pelicula>()

        peliculas.get().addOnSuccessListener { documents ->
            Log.d(TAG, "Número de documentos recuperados: ${documents.size()}")
            if (documents.isEmpty()) {
                Log.d(TAG, "La colección está vacía")
            } else {
            for (document in documents) {
                val dia = document.getString(CAMPO_DIA) ?: ""
                val titulo = document.getString(CAMPO_TITULO) ?: ""
                val descripcion = document.getString(CAMPO_DESCRIPCION) ?: ""
                val imagen = document.getString(CAMPO_IMAGEN) ?: ""
                val pelicula = Pelicula(dia, titulo, descripcion, imagen)
                listaPeliculas.add(pelicula)
                Log.i(TAG, "------> TITULO: $titulo")
            }
            setLista(listaPeliculas)
        }
        }
    }
}