package com.dionis.imc.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dionis.imc.dao.PersonDataDao
import com.dionis.imc.model.PersonData

@Database(

    entities = [PersonData::class],
    version = 1,
    exportSchema = false
)

abstract class PersonDataBase : RoomDatabase() {

    abstract fun personDataDao(): PersonDataDao

    companion object {

        @Volatile
        private var INSTANCE: PersonDataBase? = null

        fun getInstance(context: Context): PersonDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDataBase::class.java,
                        "passhash_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}