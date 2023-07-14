package com.ariqbelajar.interntest.data

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ariqbelajar.interntest.database.UserDatabase
import com.ariqbelajar.interntest.network.ApiService
import com.ariqbelajar.interntest.network.DataItem

class UserRepository(private val userDatabase: UserDatabase, private val apiService: ApiService) {
    fun getUser(): LiveData<PagingData<DataItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                initialLoadSize = 10
            ),
            remoteMediator = UserRemoteMediator(userDatabase, apiService),
            pagingSourceFactory = {
                userDatabase.userDao().getAllUser()
            }
        ).liveData
    }
}