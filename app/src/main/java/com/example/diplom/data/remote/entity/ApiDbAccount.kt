package com.example.diplom.data.remote.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.diplom.domain.entity.Account

@Entity(
    tableName = "accounts"
)

data class ApiDbAccount(
    @PrimaryKey(autoGenerate = true) val id : Long,
    @ColumnInfo(name = "username") val username:String,
    @ColumnInfo(name = "group")val group:String,
)

fun ApiDbAccount.toAccount() = Account(
    id = this.id,
    name = this.username,
    group = this.group
)
