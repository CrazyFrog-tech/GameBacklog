package com.example.gamebacklog.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gamebacklog.model.GameCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@Database(entities = [GameCard::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameCardRoomDatabase: RoomDatabase() {
    abstract fun gameCardDao(): GameCardDao

    companion object {
        private const val DATABASE_NAME = "GAMECARD_DATABASE"

        @Volatile
        private var INSTANCE: GameCardRoomDatabase? = null

        fun getDatabase(context: Context): GameCardRoomDatabase? {
            if(INSTANCE == null) {
                synchronized(GameCardRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GameCardRoomDatabase::class.java, DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }



}

