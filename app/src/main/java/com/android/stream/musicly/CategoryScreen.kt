package com.android.stream.musicly

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.android.stream.musicly.dl.adapter.SongAdapter
import com.android.stream.musicly.dl.models.CategoryModel
import com.android.stream.musicly.dl.models.SongModel
import com.android.stream.musicly.dl.models.songPlayer
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryScreen(songs : List<SongModel>, category : List<CategoryModel>, context: Context, navController: NavController, songIndex : Int) {

    val selectedCategory = category[songIndex]
    val categorySongs = songs.filter {
        it.category == selectedCategory.name
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(imageVector = Icons.Default.AccountBox, contentDescription = "",
//            modifier = Modifier.size(200.dp))
        GlideImage(
            modifier = Modifier.size(150.dp),
            model = selectedCategory.coverUri,
            contentDescription = "",
        )
        Text(text = selectedCategory.name)
        LazyColumn {
            items(category[songIndex].songs.size){song ->
                CategoryListItem(categorySongs[song], context, navController)
            }
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CategoryListItem(categorySong : SongModel, context: Context, navController: NavController) {



    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp)
            .clickable {
                songPlayer.playSong(categorySong.url, context)
                navController.navigate("player")
            }
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(200.dp)
        ) {
            GlideImage(
                modifier = Modifier.size(60.dp),
                model = categorySong.imageUri,
                contentScale = ContentScale.Crop,
                contentDescription = ""
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = categorySong.title,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = categorySong.singer,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Icon(
            imageVector = Icons.Default.MoreVert, tint = Color.Gray, contentDescription = ""
        )
    }
}