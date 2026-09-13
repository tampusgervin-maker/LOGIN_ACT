package com.example.login_act

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ValidationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val username = intent.getStringExtra("USERNAME") ?: ""
        val password = intent.getStringExtra("PASSWORD") ?: ""

        val resultIntent = Intent()
        
        // Hardcoded values for validation
        if (username == "admin" && password == "password123") {
            resultIntent.putExtra("USERNAME", username)
            setResult(RESULT_OK, resultIntent)
        } else {
            resultIntent.putExtra("ERROR", "Invalid username or password")
            setResult(RESULT_CANCELED, resultIntent)
        }
        
        finish()
    }
}
