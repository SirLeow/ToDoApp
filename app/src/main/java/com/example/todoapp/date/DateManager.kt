package com.example.todoapp.date

import androidx.fragment.app.FragmentManager
import com.example.todoapp.databinding.ActivityAddBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class DateManager {
     fun pickDate(binding: ActivityAddBinding, frag: FragmentManager) {
        binding.tiData.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time * -1)
                binding.tiData.setText(Date(it - offset).format())
            }
            datePicker.show(frag, "DATE_PICKER_TAG")
        }
    }

    private val locale = Locale("pt","BR")
    private fun Date.format():String = SimpleDateFormat("dd/MM/yyyy", locale).format(this)
}