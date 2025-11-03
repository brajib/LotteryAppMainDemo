package com.example.lotteryapp

class LotteryRepo {
    fun generateRandomNumber(count: Int): String {
        val number = (100_000..999_999).random()
        return number.toString()
    }
}