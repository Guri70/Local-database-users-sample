package com.app.localdatabase.roomDatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AuthenticationDao {

    @Insert
    fun insertDetails(data: UserModel?)

    @Query("select * from LoginDetails")
    fun getDetails(): LiveData<List<UserModel>>

    @Query("delete from LoginDetails")
    fun deleteAllData()

    @Delete
    fun delete(model: UserModel?)

    @Update
    fun update(model: UserModel?)
}