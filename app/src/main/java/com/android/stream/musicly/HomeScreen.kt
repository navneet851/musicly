package com.android.stream.musicly

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
            CategoryListItem(categories[index], navController)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CategoryListItem(category: CategoryModel, navController: NavController) {
    Column(
        modifier = Modifier
            .clickable {
                navController.navigate("categorySongs")
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