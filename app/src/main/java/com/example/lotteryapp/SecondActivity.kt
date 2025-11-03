package com.example.lotteryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.lotteryapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val viewModel: LotteryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("Username") ?: "User"
        viewModel.setUsername(name)

        // Observe LiveData
        viewModel.randomNumbers.observe(this, Observer { numbers ->
            binding.textView2.text = numbers
        })

        // Generate numbers
        viewModel.generateNumbers()

        // Share button listener
        binding.sendButton.setOnClickListener {
            shareResults(name, binding.textView2.text.toString())
        }
    }

    private fun shareResults(username: String, numbers: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "$username's Lottery Numbers")
            putExtra(Intent.EXTRA_TEXT, "The lottery numbers are: $numbers")
        }
        startActivity(intent)
    }
}