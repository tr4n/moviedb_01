package com.tr4n.moviedb.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tr4n.moviedb.data.source.AuthenticationRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {

    private val authenticationRepository : AuthenticationRepository by inject()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    fun getRequestToken() {
        viewModelScope.launch {
            println(authenticationRepository.getRequestToken())
        }
    }
    val text: LiveData<String> = _text
}
