package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androiddevs.mvvmnewsapp.models.Article
import com.androiddevs.mvvmnewsapp.converter.Converters

@Database(entities = [Article::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDataBase :RoomDatabase(){
    abstract fun getArticleDao():ArticleDao

    companion object{
        @Volatile
        private var INSTANCE: ArticleDataBase? = null
        fun getDataBase(context: Context): ArticleDataBase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    ArticleDataBase::class.java,
                    "article_database.db").build()
                INSTANCE = instance
                return instance
            }

}
    }
}