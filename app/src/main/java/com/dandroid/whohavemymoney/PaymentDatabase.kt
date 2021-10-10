package com.dandroid.whohavemymoney

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dandroid.whohavemymoney.dao.PaymentDao
import com.dandroid.whohavemymoney.model.Payment

@Database(entities = [Payment::class], version = 1)
abstract class PaymentDatabase: RoomDatabase() {
    abstract fun paymentDao(): PaymentDao
}