package com.example.myapplicationcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
//import android.os.Build
//import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
//import androidx.annotation.RequiresApi
//import com.example.myapplicationcalc.R.id.tvAgeInMinutes
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView?= null
    private var tvAgeInMinutes : TextView?= null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnDatePicker.setOnClickListener{

            clickDatePicker()

        }
    }



    private fun clickDatePicker(){
        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _ , selectedyear, selectedmonth, selecteddayOfMonth ->
                Toast.makeText(this,"Year was $selectedyear,Month was ${selectedmonth+1},day of month was $selecteddayOfMonth",Toast.LENGTH_LONG).show()
                val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
                tvSelectedDate?.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate=sdf.parse(selectedDate)
                theDate?.let{

                    val selectedDateInMinutes= theDate.time/(60000)
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time/(60000)
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMinutes?.text=differenceInMinutes.toString()
                    }

                }


            },
            year,
            month,
            day

        )
        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
        dpd.show()

    }
}