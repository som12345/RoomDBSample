package com.example.stylobillingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_customer_actvity.*
import kotlinx.android.synthetic.main.activity_home.*

class AddCustomerActvity : AppCompatActivity() {
    private lateinit var viewModel: RoomDBViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_customer_actvity)
        viewModel = RoomDBViewModel(this)
        button2.setOnClickListener {
            if(editTextTextPersonName.text.toString().isNotEmpty() ||
                    editTextTextPersonName3.text.toString().isNotEmpty() ||
                    editTextPhone.text.toString().isNotEmpty()) {
                viewModel.addCustomerData(
                    (0..10000).random(),
                    editTextTextPersonName.text.toString(),
                    editTextTextPersonName3.text.toString(),
                    editTextPhone.text.toString()
                )

            } else {
                Toast.makeText(this@AddCustomerActvity,"Please fill all the details", Toast.LENGTH_SHORT).show()
            }
        }
    }

}