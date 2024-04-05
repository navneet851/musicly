package com.android.stream.musicly.dl.models

data class CategoryModel(
    val name: String,
    val coverUri: String,
    val songs : List<SongModel>
){
    constructor() : this("", "", listOf())
}

data class SongModel(
    val id : String,
    val title : String,
    val singer : String,
    val imageUri : String,
    val url : String
)
