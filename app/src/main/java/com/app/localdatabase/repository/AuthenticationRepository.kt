package com.app.localdatabase.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.app.localdatabase.roomDatabase.AuthenticationDao
import com.app.localdatabase.roomDatabase.AuthenticationDatabase
import com.app.localdatabase.roomDatabase.UserModel
import java.util.concurrent.Executors

class AuthenticationRepository(
    application: Application
) {

    private var loginDao: AuthenticationDao? = null
    private var allData: LiveData<List<UserModel>>? = null

    companion object{
        private const val NO_OF_THREADS = 4
    }

    init {
        val db = AuthenticationDatabase.getDatabase(application)
        loginDao = db.loginDoa()
        allData = loginDao!!.getDetails()
    }

    fun insert(userModel: UserModel?) {
        Executors.newFixedThreadPool(NO_OF_THREADS)
            .execute { loginDao?.insertDetails(userModel) }
    }

    fun update(userModel: UserModel?) {
        Executors.newFixedThreadPool(NO_OF_THREADS)
            .execute { loginDao?.update(userModel) }
    }

    fun delete(userModel: UserModel?) {
        Executors.newSingleThreadScheduledExecutor()
            .execute { loginDao?.delete(userModel) }
    }

    fun deleteData() {
        loginDao!!.deleteAllData()
    }

    fun getAllData(): LiveData<List<UserModel>> {
        return allData!!
    }


}