package com.example.ltech.data.models.database.articlesTable

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ARTICLES_TABLE")
data class ArticlesEntity(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    val id: String,
    @ColumnInfo(name = "DATE")
    val date: String,
    @ColumnInfo(name = "IMAGE")
    val image: String,
    @ColumnInfo(name = "SORT")
    val sort: Int,
    @ColumnInfo(name = "TEXT")
    val text: String,
    @ColumnInfo(name = "TITLE")
    val title: String,
)