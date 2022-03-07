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


private lateinit var binding: ActivityMainBinding
val data = DateManager()
val time= TimeManager()
lateinit var db: HelperDB

private lateinit var rvList: RecyclerView
private lateinit var adapter: RVAdapter

class MainActivity : AppCompatActivity(), ItemSelected {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        rvList = binding.rvList
        val view = binding.root
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

    override fun clickItem(task: Task) {
        //super.clickItem(task)
        db.onDelete(task.id)
        adapter.updateList(db.onSearch("*"))
    }

    private fun addTask(){
        binding.btAddTask.setOnClickListener{
            startActivity(Intent(this, CreateTaskActivity::class.java))
        }

        binding.btSearch.setOnClickListener{

        }
    }

    fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}