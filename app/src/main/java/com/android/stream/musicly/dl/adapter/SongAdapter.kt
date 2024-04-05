package com.android.stream.musicly.dl.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.stream.musicly.dl.models.CategoryModel
import com.android.stream.musicly.dl.models.SongModel
import com.google.firebase.firestore.FirebaseFirestore

class SongAdapter {
    fun getSong(songId : String) : LiveData<SongModel> {
        val livedata = MutableLiveData<SongModel>()
        FirebaseFirestore.getInstance().collection("songs")
            .document(songId).get()
            .addOnSuccessListener {
                val SongList = it.toObject(SongModel::class.java)
                livedata.value = SongList!!
//                categoryList.forEach { category ->
//                    Log.d("songslist", category.songs.size.toString())
//                }
            }
        return  livedata
    }
}