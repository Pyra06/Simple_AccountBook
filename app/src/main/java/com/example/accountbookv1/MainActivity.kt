package com.example.accountbookv1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.table_row.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DataBaseHandler(this)
        refreshSave()

        btn_Save.setOnClickListener {

            if (etvName.text.toString().isNotEmpty()) {

                val radioId = radioGroup.checkedRadioButtonId
                val radioFun = findViewById<RadioButton>(radioId)

                val user = User()
                user.name = etvName.text.toString()
                user.type = radioFun.text.toString()

                db.insertData(user)
            } else {
                Toast.makeText(this, "PLEASE FILL THE FIELD FIRST", Toast.LENGTH_SHORT).show()
            }
            refreshSave()
        }

        btn_Delete.setOnClickListener() {
            db.deleteData(etvID.text.toString().toInt())
            refreshSave()
        }
    }

    private fun refreshSave() {
        val db = DataBaseHandler(this)
        val data = db.getUser(this)
        val adapter = MainAdapter(data)
        val rv: RecyclerView = findViewById(R.id.recyclerViewTable)
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager
        rv.adapter = adapter
    }

    override fun onResume() {
        refreshSave()
        super.onResume()
    }
}
