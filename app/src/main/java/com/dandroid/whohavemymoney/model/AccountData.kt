package com.dandroid.whohavemymoney.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountData(
    @PrimaryKey@ColumnInfo(name = "InsertDate")
    val insertDate :String?,

    @ColumnInfo(name = "gubun")
    val gubun: String,

    @ColumnInfo(name = "date")
    val date: String?,

    @ColumnInfo(name = "price")
    val price: Int,

    @ColumnInfo(name = "place")
    val place: String?,

    @ColumnInfo(name = "memo")
    val memo: String
)