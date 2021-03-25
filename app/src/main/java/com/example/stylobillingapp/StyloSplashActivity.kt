package com.example.stylobillingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler


class StyloSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showSplash()
    }

    private fun showSplash() {
        Handler().postDelayed({
            val mainIntent = Intent(this@StyloSplashActivity, HomeActivity::class.java)
            this@StyloSplashActivity.startActivity(mainIntent)
            this@StyloSplashActivity.finish()
        }, 3000)
    }
}