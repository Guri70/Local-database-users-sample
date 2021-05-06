package com.app.localdatabase.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "LoginDetails")
class UserModel : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var id: Int? = null

    @ColumnInfo(name = "Email")
    var email: String? = null

    @ColumnInfo(name = "Password")
    var password: String? = null

    @ColumnInfo(name = "Name")
    var name: String? = null

    @ColumnInfo(name = "Phone")
    var phone: String? = null

    @ColumnInfo(name = "Dob")
    var dob: String? = null

    @ColumnInfo(name = "Gender")
    var gender: String? = null

}