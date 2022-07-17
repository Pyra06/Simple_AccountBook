package com.example.accountbookv1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.table_row.view.*

class MainAdapter(private val accounts : ArrayList<User>): RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val idList: TextView = itemView.textIDList1
        val nameList: TextView = itemView.textNameList1
        val typeList: TextView = itemView.textTypeList1
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.table_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val account : User = accounts[position]
        holder.idList.text = account.id.toString()
        holder.nameList.text = account.name
        holder.typeList.text = account.type
    }
}

