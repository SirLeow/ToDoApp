package com.example.todoapp.hour

import androidx.fragment.app.FragmentManager
import com.example.todoapp.activity.time
import com.example.todoapp.databinding.ActivityAddBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class TimeManager {
    fun pickTime(binding: ActivityAddBinding, frag: FragmentManager){
        binding.tiHora.setOnClickListener{
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
                timePicker.addOnPositiveButtonClickListener{
                    binding.tiHora.setText(format(timePicker))
                }
            timePicker.show(frag, null)
        }
    }

    private fun format(time: MaterialTimePicker):String{
        when{
            time.hour <10 && time.minute <10 -> return "0${time.hour}:0${time.minute}"
            time.hour <10 -> return "0${time.hour}:${time.minute}"
            time.minute <10 -> return "${time.hour}:0${time.minute}"
        }
        return "${time.hour}:${time.minute}"
    }
}