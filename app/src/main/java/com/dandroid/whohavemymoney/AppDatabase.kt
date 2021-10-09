package com.dandroid.whohavemymoney

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dandroid.whohavemymoney.dao.AccountDao
import com.dandroid.whohavemymoney.model.AccountData

@Database(entities = [AccountData::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun accountDao() : AccountDao
}