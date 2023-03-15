package com.example.ltech.di

import android.content.Context
import androidx.room.Room
import com.example.ltech.data.models.database.TechTestAppDatabase
import com.example.ltech.data.models.database.articlesTable.ArticlesDao
import com.example.ltech.data.models.database.authenticationTable.success.AuthenticationSuccessDao
import com.example.ltech.utils.Constants.ROOM_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): TechTestAppDatabase {
        return Room.databaseBuilder(
            context,
            TechTestAppDatabase::class.java,
            ROOM_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideArticlesDao(database: TechTestAppDatabase): ArticlesDao {
        return database.articlesDao()
    }

    @Provides
    @Singleton
    fun provideAuthenticationSuccessDao(database: TechTestAppDatabase): AuthenticationSuccessDao {
        return database.authenticationSuccessDao()
    }
}