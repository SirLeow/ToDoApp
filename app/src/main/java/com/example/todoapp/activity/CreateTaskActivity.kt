package com.example.todoapp.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityAddBinding


lateinit var addBinding: ActivityAddBinding

class CreateTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addBinding = ActivityAddBinding.inflate(layoutInflater)
        val view = addBinding.root
        setContentView(view)
        backListener()
        data.pickDate(addBinding,supportFragmentManager)
        time.pickTime(addBinding,supportFragmentManager)
        addTask()
        if(parcelableTask?.id != -1){
            addBinding.toolbar.title = "Editar Tarefa"
            addBinding.tiDescricao.setText(parcelableTask?.descricao ?: "")
            addBinding.tiData.setText(parcelableTask?.data ?: "")
            addBinding.tiHora.setText(parcelableTask?.hora ?: "")

        }
        //addBinding.btSaveTask.setBackgroundColor(Color.GREEN)

    }

    private fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    private fun backListener() {
        addBinding.toolbar.setNavigationOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private fun addTask(){
        addBinding.btSaveTask.setOnClickListener{
                if (addBinding.tiDescricao.text.isNullOrEmpty()) showToast("Descrição obrigatória")
                else {
                    if(parcelableTask?.id != -1){
                        db.onDelete(parcelableTask?.id?:-1)
                        db.onAdd(addBinding)
                    }
                startActivity(Intent(this, MainActivity::class.java))
                }
        }
    }

}