package com.example.stylobillingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stylobillingapp.roomdatabase.User
import kotlinx.android.synthetic.main.activity_show_customer2.*
import android.content.Intent
import android.net.Uri
import android.widget.Toast


class ShowCustomerActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: ShowUserViewModel
    private var isDeleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_customer2)
        viewModel = ShowUserViewModel(this)
        viewModel.getData()
        setupObserver()
        setupUI()

        adapter.onItemClick = { contact ->
            val url = "https://api.whatsapp.com/send?phone=${"+91" + contact.ph}"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }

        adapter.onItemLongClick = { contact ->
            viewModel.deleteUser(contact)

        }

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                Log.d("onQueryTextChange", "query: $query")
                adapter.filter.filter(query)
                return true
            }
        })


    }


    private fun setupObserver() {
        viewModel.users.observe(this, {
            renderList(it, isDeleted)

        })

        viewModel.isDelete.observe(this, {
            when (it) {
                true -> {
                    isDeleted = true
                    viewModel.getData()
                    Toast.makeText(
                        this@ShowCustomerActivity,
                        "User is Deleted", Toast.LENGTH_SHORT
                    )
                        .show()
                }
                else -> {
                    isDeleted = false
                    Toast.makeText(
                        this@ShowCustomerActivity,
                        "User is not deleted",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        })
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            UserAdapter(
                arrayListOf()
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }


    private fun renderList(users: List<User>, isDeleted: Boolean) {
        adapter.addData(users, isDeleted)
        adapter.notifyDataSetChanged()
    }


}