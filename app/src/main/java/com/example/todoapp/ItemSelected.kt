package com.example.todoapp

import com.example.todoapp.recycler_view.Task

interface ItemSelected {
    fun clickItem(task:Task, tag:String){}
}