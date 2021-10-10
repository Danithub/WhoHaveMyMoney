package com.dandroid.whohavemymoney.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Payment(
    @PrimaryKey@ColumnInfo(name = "name")
    val name :String
)