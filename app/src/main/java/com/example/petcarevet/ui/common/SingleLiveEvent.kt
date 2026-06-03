package com.example.petcarevet.ui.common

import androidx.lifecycle.MutableLiveData

class SingleLiveEvent<T> : MutableLiveData<T>() {
    fun emit(value: T) {
        postValue(value)
    }
}
