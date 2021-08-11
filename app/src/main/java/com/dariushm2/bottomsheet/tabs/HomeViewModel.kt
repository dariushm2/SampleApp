package com.dariushm2.bottomsheet.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val data: LiveData<String> = liveData {
        emit("Hello World!")
    }

    override fun onCleared() {
        super.onCleared()

    }
}