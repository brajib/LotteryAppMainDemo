package com.example.lotteryapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lotteryapp.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    // ViewBinding replaces findViewById for cleaner and safer code
    private lateinit var binding: ActivityMainBinding

    // ViewModel to store and manage UI-related data
    private val viewModel: LotteryViewModel by viewModels()
    companion object {
        private const val TAG = "MainActivityLifecycle"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"Oncreate called")
        // Only call edge-to-edge if not running under Robolectric
        val underTest = isRunningUnitTest()
        if (!underTest) {
            enableEdgeToEdge()
        }
        // Inflate layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle window insets for immersive UI
        if (!underTest) {
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

        // Handle generate button click
        binding.generateBtn.setOnClickListener {
            val username = binding.editText2.text.toString().trim()

            if (username.isNotEmpty()) {
                // Update ViewModel
                viewModel.setUsername(username)

                // Pass username to next screen
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("Username", username)
                }
                startActivity(intent)
            } else {
                binding.editText2.error = "Please enter your name"
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
    internal fun isRunningUnitTest(): Boolean {
        return try {
            Class.forName("org.robolectric.Robolectric")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }
}