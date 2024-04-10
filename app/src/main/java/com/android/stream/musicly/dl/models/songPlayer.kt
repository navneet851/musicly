package com.android.stream.musicly.dl.models

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

object songPlayer {
    var player : ExoPlayer? = null
    fun playSong(song : String, context: Context) {
        if (player == null){
            player = ExoPlayer.Builder(context).build()
        }
        val mediaItem = MediaItem.fromUri(song)
        player!!.setMediaItem(mediaItem)
        player!!.prepare()
        player!!.playWhenReady = true

    }
}