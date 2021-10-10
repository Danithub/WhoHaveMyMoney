package com.dandroid.whohavemymoney.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.dandroid.whohavemymoney.model.Payment

@Dao
interface PaymentDao{
    @Query("SELECT * FROM Payment")
    fun getAll():List<Payment>

    @Insert
    fun insertPayment(new : Payment)

    @Delete
    fun deletePayment(old : Payment)
}