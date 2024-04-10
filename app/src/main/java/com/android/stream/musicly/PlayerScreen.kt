package com.android.stream.musicly

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerControlView
import androidx.media3.ui.PlayerView
import com.android.stream.musicly.dl.models.songPlayer

@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(context: Context) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        AndroidView(factory = { context ->
            PlayerView(context).apply {
                player = songPlayer.player
            }
        })
    }
}