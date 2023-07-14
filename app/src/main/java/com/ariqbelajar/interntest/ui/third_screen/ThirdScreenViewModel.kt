package com.ariqbelajar.interntest.ui.third_screen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ariqbelajar.interntest.data.UserRepository
import com.ariqbelajar.interntest.di.Injection
import com.ariqbelajar.interntest.network.DataItem

class ThirdScreenViewModel(userRepository: UserRepository) : ViewModel()  {
    val user: LiveData<PagingData<DataItem>> = userRepository.getUser().cachedIn(viewModelScope)

}

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThirdScreenViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}