package com.example.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.helpers.HelperDB
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.date.DateManager
import com.example.todoapp.hour.TimeManager
import com.example.todoapp.ItemSelected
import com.example.todoapp.recycler_view.RVAdapter
import com.example.todoapp.recycler_view.Task


lateinit var mainBinding: ActivityMainBinding
val data = DateManager()
val time= TimeManager()
var parcelableTask:Task? = null
lateinit var db: HelperDB
private lateinit var rvList: RecyclerView
private lateinit var adapter: RVAdapter

class MainActivity : AppCompatActivity(), ItemSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        rvList = mainBinding.rvList
        val view = mainBinding.root
        setContentView(view)

        db = HelperDB(this)
        adapter = RVAdapter(this)
        bindViews()
        addTask()
        //adapter.deleteTask(view)
    }

    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
        adapter.updateList(db.onSearch("*"))
    }

    override fun clickItem(task: Task, tag: String) {
        //super.clickItem(task)
        when(tag){
            "DELETE" -> db.onDelete(task.id)
            "EDIT"   -> editTask(task)
        }
        adapter.updateList(db.onSearch("*"))
    }

    private fun addTask(){
        mainBinding.btAddTask.setOnClickListener{
            startActivity(Intent(this, CreateTaskActivity::class.java))
        }
    }

    private fun editTask(task:Task){
        parcelableTask = task
        //db.onDelete(task.id)
        val intent = Intent(this, CreateTaskActivity::class.java)
        startActivity(intent)
    }

    fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}