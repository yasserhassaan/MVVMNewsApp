package com.androiddevs.mvvmnewsapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.androiddevs.mvvmnewsapp.converter.Converters

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val int: Int?= null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    //@TypeConverters(Converters::class)
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)