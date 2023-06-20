package com.example.diplom.data.repo

import com.example.diplom.data.dataSource.ISibsutisRemoteServices
import com.example.diplom.data.remote.entity.ApiHeadSendList
import com.example.diplom.domain.repo.IHeadGroupListSendRepository

class HeadGroupListSendRepository(private val api:ISibsutisRemoteServices): IHeadGroupListSendRepository {
    override suspend fun sendgrouplistforconfirmation(grouplist: ApiHeadSendList):String {
        return api.sendGroupList(grouplist).body()!!
    }
}
