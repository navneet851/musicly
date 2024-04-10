package com.android.stream.musicly

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.stream.musicly.dl.adapter.SongAdapter
import com.android.stream.musicly.dl.models.CategoryModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun HomeScreen(categories : List<CategoryModel>, navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(categories.size){index ->
//            val songS = SongAdapter().getSong(categories[index].songs[index])
//            if (songS.value != null) {
//                Log.d("check", songS.value!!.title.toString())
//            }
//            else{
//                Log.d("check", "isnulldata")
//            }
            Log.d("checking", categories[index].songs.toString())
            CategoryListItem(categories[index]){
                navController.navigate("categorySongs/${index}")

            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CategoryListItem(category: CategoryModel, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(imageVector = Icons.Default.AccountBox, contentDescription = "",
//            modifier = Modifier.size(200.dp))
        GlideImage(
            modifier = Modifier.size(150.dp),
            model = category.coverUri,
            contentDescription ="",
        )
        Text(text = category.name)
    }
}