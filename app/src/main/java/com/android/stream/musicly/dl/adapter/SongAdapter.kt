package com.android.stream.musicly.dl.adapter

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.stream.musicly.dl.models.CategoryModel
import com.android.stream.musicly.dl.models.SongModel
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.FirebaseFirestore

class SongAdapter {
    fun getSong(songId: String): LiveData<SongModel?> {
        val livedata = MutableLiveData<SongModel?>()
        FirebaseFirestore.getInstance().collection("songs")
            .document(songId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val song = documentSnapshot.toObject(SongModel::class.java)
                livedata.value = song
            }
        return livedata
    }

    fun getSongs(): LiveData<List<SongModel>> {
        val livedata = MutableLiveData<List<SongModel>>()
        FirebaseFirestore.getInstance().collection("songs")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val songs = querySnapshot.toObjects(SongModel::class.java)
                livedata.value = songs
            }
        return livedata
    }
}