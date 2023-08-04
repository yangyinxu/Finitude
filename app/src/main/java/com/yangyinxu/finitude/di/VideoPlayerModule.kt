package com.yangyinxu.finitude.di

import android.app.Application
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.yangyinxu.finitude.presentation.player.MetadataReader
import com.yangyinxu.finitude.presentation.player.MetadataReaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object VideoPlayerModule {

    @Provides
    @ViewModelScoped
    fun provideVideoPlayer(
        app: Application
    ): Player {
        return ExoPlayer.Builder(app)
            .build()
    }

    @Provides
    @ViewModelScoped
    fun provideMetadataReader(
        app: Application
    ): MetadataReader {
        return MetadataReaderImpl(app)
    }
}