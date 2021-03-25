package com.example.stylobillingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bt_add_customer.setOnClickListener {
            startActivity(Intent(this@HomeActivity,
                AddCustomerActvity::class.java))
        }

        bt_show_customer.setOnClickListener {
            startActivity(Intent(this@HomeActivity,
                ShowCustomerActivity::class.java))
        }
    }
}