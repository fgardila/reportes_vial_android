package com.fgardila.nativapps.di

import android.content.Context
import androidx.room.Room
import com.fgardila.nativapps.data.ReportDao
import com.fgardila.nativapps.data.ReportDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ReportDatabase::class.java,
        "reports_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ReportDatabase) = database.reportDao()
}