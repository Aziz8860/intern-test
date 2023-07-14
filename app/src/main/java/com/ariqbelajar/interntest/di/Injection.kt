package com.ariqbelajar.interntest.di

import android.content.Context
import com.ariqbelajar.interntest.data.UserRepository
import com.ariqbelajar.interntest.database.UserDatabase
import com.ariqbelajar.interntest.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val database = UserDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return UserRepository(database, apiService)
    }
}