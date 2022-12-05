package com.tr4n.moviedb.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent{
    var exception = MutableLiveData<Exception>()
}
