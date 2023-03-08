package com.fgardila.nativapps.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "report_table")
data class Report(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val urlImage: String
)