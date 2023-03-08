package com.fgardila.nativapps.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fgardila.nativapps.data.model.Report

@Database(entities = [Report::class], version = 1, exportSchema = false)
abstract class ReportDatabase: RoomDatabase() {

    abstract fun reportDao(): ReportDao
}