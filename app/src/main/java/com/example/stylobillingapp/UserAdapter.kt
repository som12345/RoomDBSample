package com.example.stylobillingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.stylobillingapp.roomdatabase.User
import kotlinx.android.synthetic.main.item_layout.view.*

class UserAdapter(
    private var users: ArrayList<User>
) : RecyclerView.Adapter<UserAdapter.DataViewHolder>(), Filterable {
    var onItemClick: ((User) -> Unit)? = null
    lateinit var onItemLongClick: ((User) -> Boolean)
    val newList = mutableListOf<User>()

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.tv_name.text = user.name
            itemView.tv_add.text = user.add
            itemView.tv_phone.text = user.ph
            itemView.lnr_container.setOnClickListener {
                onItemClick?.invoke(user)
            }

            itemView.lnr_container.setOnLongClickListener {
                onItemLongClick.invoke(user)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<User>, isDeleted: Boolean) {
        if(isDeleted) {
            users.clear()
            newList.clear()
            users.addAll(list)
            newList.addAll(list)
        } else {
            users.addAll(list)
            newList.addAll(list)
        }

    }

    fun addNewList(list: List<User>) {
        users.clear()
        users.addAll(list)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isNotEmpty()) {
                    val  resultList = mutableListOf<User>()
                    for (row in users) {
                        if (row.name?.toLowerCase()
                                ?.contains(constraint.toString().toLowerCase()) == true
                        ) {
                            resultList.add(row)
                        }
                    }
                    users = resultList as ArrayList<User>
                } else {
                    addNewList(newList)
                }
                val filterResults = FilterResults()
                filterResults.values = users
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                try {
                    users = results?.values as ArrayList<User>
                    this@UserAdapter.notifyDataSetChanged()
                } catch (e: Exception) {
                }
            }
        }

    }
}