package com.android.stream.musicly.dl.adapter

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.stream.musicly.dl.models.CategoryModel
import com.android.stream.musicly.dl.models.SongModel
import com.google.firebase.firestore.FirebaseFirestore

class SongAdapter {
    fun getSongs(songIds: List<String>): State<List<SongModel>> {
        val songs = mutableStateOf(emptyList<SongModel>())

        songIds.forEach { songId ->
            FirebaseFirestore.getInstance().collection("songs")
                .document(songId).get()
                .addOnSuccessListener {
                    val song = it.toObject(SongModel::class.java)
                    if (song != null) {
                        songs.value += song
                    }
                }.addOnFailureListener {
                    Log.d("FirebaseError", it.localizedMessage!!)
                }
        }

        return songs
    }
}