package com.example.real_estate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout


class IntoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val findBtn = findViewById<Button>(R.id.button)
        findBtn.setOnClickListener {
            val intent = Intent (this,MainActivity::class.java)
            startActivity(intent)

        }

    }
}