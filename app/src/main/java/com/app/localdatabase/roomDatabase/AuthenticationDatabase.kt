package com.app.localdatabase.roomDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class AuthenticationDatabase : RoomDatabase() {

    abstract fun loginDoa(): AuthenticationDao

    companion object {

        private var INSTANCE: AuthenticationDatabase? = null

        fun getDatabase(context: Context): AuthenticationDatabase {
            if (INSTANCE == null) {
                synchronized(AuthenticationDatabase::class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext, AuthenticationDatabase::class.java,
                            "LOGIN_DATABASE"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return INSTANCE!!
        }

    }
}