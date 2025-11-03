package com.example.lotteryapp

class LotteryRepo {
    fun generateRandomNumber(count: Int): String {
        val safeCount = count.coerceAtLeast(1)
        val numbers = List(safeCount) { (1..42).random() }
        return numbers.joinToString(", ")
    }
}