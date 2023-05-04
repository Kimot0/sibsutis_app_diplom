package com.example.diplom.data.remote.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.diplom.domain.entity.Account

@Entity(
    tableName = "accounts",
    indices = [Index("email",unique=true)]
)

data class ApiDbAccount(
    @PrimaryKey(autoGenerate = true) val id : Long,
    @ColumnInfo(name = "username", collate = ColumnInfo.NOCASE) val username:String,
    val password:String,
    val group:String,
    @ColumnInfo(name = "created_at")val createdAt : Long,
)
fun ApiDbAccount.toAccount() = Account(
    id = this.id,
    name = this.username,
    group = this.group
)
