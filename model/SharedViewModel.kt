package com.ivangarcia.apiproducts.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    // variable to contain message whenever
    // it gets changed/modified(mutable)
    val message = MutableLiveData<String>()

    // function to send message
    fun sendMessage(jsonSt: String) {
        message.value = jsonSt
    }
}
