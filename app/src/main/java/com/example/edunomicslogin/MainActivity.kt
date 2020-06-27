package com.example.edunomicslogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginButton=findViewById<Button>(R.id.buttonLogin)
        loginButton.setOnClickListener {
            val intent=Intent(this, ChatMainActivity::class.java)
            startActivity(intent)
        }
    }
}
