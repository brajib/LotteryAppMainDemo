package com.example.lotteryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LotteryViewModel: ViewModel() {

    private val repository = LotteryRepo()

    private val _randomNumbers = MutableLiveData<String>()
    val randomNumbers: LiveData<String> = _randomNumbers

    private val username = MutableLiveData<String>()
    val userName: LiveData<String> = username

    fun setUsername(name: String) {
        username.value = name
    }

    fun generateNumbers() {
        val result = repository.generateRandomNumber(6)
        _randomNumbers.value = result
    }
}