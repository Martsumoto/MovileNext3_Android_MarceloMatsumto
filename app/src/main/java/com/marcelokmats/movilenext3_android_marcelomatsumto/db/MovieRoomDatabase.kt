package com.marcelokmats.movilenext3_android_marcelomatsumto.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.Movie
import com.marcelokmats.movilenext3_android_marcelomatsumto.api.MovieTicket
import com.marcelokmats.movilenext3_android_marcelomatsumto.dao.MovieDao

@Database(entities = [Movie::class, MovieTicket::class], version = 1, exportSchema = false)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase {
            if (instance == null) {
                synchronized(MovieRoomDatabase::class.java) {
                    // Db creation
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieRoomDatabase::class.java,
                        "movie_database"
                    ).build()
                }
            }

            return instance!!
        }
    }
}