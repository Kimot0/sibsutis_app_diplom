package com.example.diplom.domain.repo

import com.example.diplom.data.remote.entity.ApiHeadSendList

interface IHeadGroupListSendRepository {
    suspend fun sendgrouplistforconfirmation(grouplist: ApiHeadSendList):String
}