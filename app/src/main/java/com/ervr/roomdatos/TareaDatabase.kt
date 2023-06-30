package com.ervr.roomdatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tarea::class], version = 1, exportSchema = false)
abstract class TareaDatabase : RoomDatabase() {
    abstract fun tareaDao(): TareaDao

    companion object {
        @Volatile
        private var INSTANCE: TareaDatabase? = null

        fun getDatabase(context: Context): TareaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TareaDatabase::class.java,
                    "tarea_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
