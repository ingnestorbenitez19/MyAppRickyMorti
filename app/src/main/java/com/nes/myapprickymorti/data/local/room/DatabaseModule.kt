package com.nes.myapprickymorti.data.local.room

import android.content.Context
import androidx.room.Room
import com.nes.myapprickymorti.data.local.dao.FavoriteCharacterDao
import com.nes.myapprickymorti.data.local.dao.ReadEpisodeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my_app_database"
        ).build()

    @Provides
    fun provideFavoriteCharacterDao(db: AppDatabase): FavoriteCharacterDao =
        db.favoriteCharacterDao()


    @Provides
    fun provideReadEpisodeDao(db: AppDatabase): ReadEpisodeDao =
        db.readEpisodeDao()

}