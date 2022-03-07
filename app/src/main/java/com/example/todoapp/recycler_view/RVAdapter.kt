package com.example.todoapp.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R

class RVAdapter : RecyclerView.Adapter<RVAdapter.ContactAdapterViewHolder>() {

    private val list: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent, false)
        return ContactAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateList(list: List<Task>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ContactAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val tvTask: TextView = itemView.findViewById(R.id.tv_task)
        private val tvData: TextView = itemView.findViewById(R.id.tv_data)
        private val tvHora: TextView = itemView.findViewById(R.id.tv_hora)

        fun bind(task: Task){
            tvTask.text = task.descricao
            tvData.text = task.data
            tvHora.text = task.hora
        }
    }

    //fun deleteTask(itemView: View){
    //val imageButton: ImageButton = itemView.findViewById(R.id.ib_delete)
    //    imageButton.setOnClickListener{

      //  }
    //}
}