package com.dandroid.whohavemymoney.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dandroid.whohavemymoney.model.AccountData

@Dao
interface AccountDao{
    @Query("SELECT * FROM AccountData")
    fun getAll():List<AccountDao>

    @Insert
    fun insertAccountData(data : AccountData)

    @Delete
    fun deleteAccountData(uid : Int)

    @Query("SELECT * FROM AccountData WHERE date LIKE :date")
    fun getDataByDate(date : String)
}