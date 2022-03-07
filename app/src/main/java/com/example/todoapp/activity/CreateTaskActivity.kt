package com.example.todoapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityAddBinding
import com.example.todoapp.recycler_view.Task


private lateinit var binding: ActivityAddBinding

class CreateTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        backListener()
        data.pickDate(binding,supportFragmentManager)
        time.pickTime(binding,supportFragmentManager)
        addTask()

    }

    private fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun backListener() {
        binding.toolbar.setNavigationOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private fun addTask(){
        binding.btSaveTask.setOnClickListener{
                if (binding.tiDescricao.text.isNullOrEmpty()) showToast("Descrição obrigatória")
                else {
                    db.onAdd(
                        Task(
                            descricao = binding.tiDescricao.text.toString(),
                            data = binding.tiData.text.toString(),
                            hora = binding.tiHora.text.toString()
                        )
                    )
                startActivity(Intent(this, MainActivity::class.java))
                }
        }
    }
}