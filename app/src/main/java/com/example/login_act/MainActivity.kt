package com.example.login_act

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.login_act.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val loginLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val username = result.data?.getStringExtra("USERNAME") ?: "User"
            showWelcomeMessage(username)
        } else {
            val error = result.data?.getStringExtra("ERROR") ?: "Login failed"
            showErrorMessage(error)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text.toString()
            val password = binding.passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showErrorMessage("Please fill in all fields")
            } else {
                val intent = Intent(this, ValidationActivity::class.java).apply {
                    putExtra("USERNAME", username)
                    putExtra("PASSWORD", password)
                }
                loginLauncher.launch(intent)
            }
        }
    }

    private fun showWelcomeMessage(username: String) {
        binding.instructionText.visibility = View.GONE
        binding.usernameInput.visibility = View.GONE
        binding.passwordInput.visibility = View.GONE
        binding.loginButton.visibility = View.GONE
        binding.errorText.visibility = View.GONE

        binding.welcomeText.text = "Welcome $username!"
        binding.welcomeText.visibility = View.VISIBLE
    }

    private fun showErrorMessage(message: String) {
        binding.errorText.text = message
        binding.errorText.visibility = View.VISIBLE
    }
}
