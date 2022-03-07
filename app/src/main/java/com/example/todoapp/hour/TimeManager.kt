package com.example.todoapp.hour

import androidx.fragment.app.FragmentManager
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
        return "${time.hour}:${time.minute}"
    }
}