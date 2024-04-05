package com.android.stream.musicly.dl.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.stream.musicly.dl.models.CategoryModel
import com.google.firebase.firestore.FirebaseFirestore

class CategoryAdapter {
    fun getCategories() : LiveData<List<CategoryModel>> {
        val livedata = MutableLiveData<List<CategoryModel>>()
        FirebaseFirestore.getInstance().collection("category")
            .get().addOnSuccessListener {
                val categoryList = it.toObjects(CategoryModel::class.java)
                livedata.value = categoryList
//                categoryList.forEach { category ->
//                    Log.d("songslist", category.songs.size.toString())
//                }
            }
        return  livedata
    }
}