package com.android.stream.musicly

import android.icu.util.ULocale.Category
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.stream.musicly.dl.adapter.CategoryAdapter
import com.android.stream.musicly.dl.models.CategoryModel
import com.android.stream.musicly.ui.theme.MusiclyTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val categoriesLiveData = CategoryAdapter().getCategories()
        categoriesLiveData.observe(this) { categories ->
            setContent {
                MusiclyTheme {
                    // A surface container using the 'background' color from th
                    // e theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        val navController = rememberNavController()
                        NavHost(navController = navController, startDestination = "home"){
                            composable("home"){
                                HomeScreen(categories = categories, navController)
                            }
                            composable(
                                route = "categorySongs/{songIndex}",
                                arguments = listOf(
                                    navArgument("songIndex"){
                                        type = NavType.IntType
                                    }
                                )
                            ){
                                CategoryScreen(category = categories, it.arguments!!.getInt("songIndex"))
                            }
                        }
                    }
                }
            }
        }
    }

}



