package com.emon.raihan.customcalender

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), NumberPicker.OnValueChangeListener {
    private lateinit var textView: TextView
    private lateinit var btnDate: Button
    private lateinit var dateValue: TextView
    private lateinit var dayPicker: NumberPicker
    private lateinit var monthPicker: NumberPicker
    private lateinit var yearPicker: NumberPicker
    private var lastDay = 0
    private val months = arrayOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        btnDate = findViewById(R.id.btnDate)

        btnDate.setOnClickListener {
            createYearPicker()
        }
    }

    @SuppressLint("SetTextI18n")
    fun createYearPicker() {
        val dialog = AlertDialog.Builder(this).setCancelable(false)
        val inflater = LayoutInflater.from(this)
        val regLayout = inflater.inflate(R.layout.dialog_year_picker, null)
        //val dateValue = reg_layout.findViewById<TextView>(R.id.dateValue)
        dateValue = regLayout.findViewById(R.id.dateValue)
        dayPicker = regLayout.findViewById(R.id.dayPicker)
        monthPicker = regLayout.findViewById(R.id.monthPicker)
        yearPicker = regLayout.findViewById(R.id.yearPicker)
        val btnOk = regLayout.findViewById<Button>(R.id.btn_ok)
        val btnCancel = regLayout.findViewById<Button>(R.id.btn_cancel)
        dialog.setView(regLayout)
        val alertDialog = dialog.create()
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val month = cal.get(Calendar.MONTH) + 1



        yearPicker.minValue = 1950
        yearPicker.maxValue = year
        yearPicker.value = year
        yearPicker.wrapSelectorWheel = false
        yearPicker.setOnValueChangedListener(this)


        monthPicker.minValue = 1
        monthPicker.maxValue = 12
        monthPicker.value = month
        monthPicker.displayedValues = months

        //monthPicker.wrapSelectorWheel = false
        monthPicker.setOnValueChangedListener(this)


        dayPicker.minValue = 1
        dayPicker.maxValue = lastDay
        dayPicker.value = day
        //dayPicker.wrapSelectorWheel = false

        dayPicker.setOnValueChangedListener(this)

        val newday: String = if (day < 10) {
            "0" + (day).toString()
        } else {
            day.toString()
        }
        val newmonth: String = if (month < 10) {
            "0" + (month).toString()
        } else {
            month.toString()
        }

        dateValue.text = newday + "/" + newmonth + "/" + yearPicker.value.toString()


        btnOk.setOnClickListener {

            val newdayt: String = if (dayPicker.value < 10) {
                "0" + (dayPicker.value).toString()
            } else {
                dayPicker.value.toString()
            }
            val newmontht: String = if (monthPicker.value < 10) {
                "0" + (monthPicker.value).toString()
            } else {
                monthPicker.value.toString()
            }
            textView.text = newdayt + "/" + newmontht + "/" + yearPicker.value.toString()

            alertDialog.dismiss()
        }

        btnCancel.setOnClickListener { alertDialog.dismiss() }


        alertDialog.show()
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        if (dayPicker == picker) {
            val newday: String = if (newVal < 10) {
                "0" + (newVal).toString()
            } else {
                newVal.toString()
            }
            dateValue.text =
                newday + "/" + monthPicker.value.toString() + "/" + yearPicker.value.toString()
        } else if (monthPicker == picker) {

            val newmonth: String = if (newVal < 10) {
                "0" + (newVal).toString()
            } else {
                newVal.toString()
            }

            val newday: String = if (dayPicker.value < 10) {
                "0" + (dayPicker.value).toString()
            } else {
                dayPicker.value.toString()
            }

            val sdf = SimpleDateFormat("dd MM yyyy")
           // val date = sdf.parse(newday + " " + newmonth + " " + yearPicker.value.toString())// all done
            // all done
            val caln: Calendar = sdf.calendar
            lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH)
            dayPicker.minValue = 1
            dayPicker.maxValue = lastDay
            Log.d("value-->", lastDay.toString())
            dateValue.text =
                newday + "/" + newmonth + "/" + yearPicker.value.toString()
        } else if (yearPicker == picker) {

            val newmonth: String = if (monthPicker.value < 10) {
                "0" + (monthPicker.value).toString()
            } else {
                monthPicker.value.toString()
            }

            val newday: String = if (dayPicker.value < 10) {
                "0" + (dayPicker.value).toString()
            } else {
                dayPicker.value.toString()
            }

            val sdf = SimpleDateFormat("dd MM yyyy")
            //val date =sdf.parse(newday + " " + newmonth + " " + yearPicker.value.toString())// all done
            // all done
            val caln: Calendar = sdf.calendar
            lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH)
            dayPicker.minValue = 1
            dayPicker.maxValue = lastDay
            Log.d("value-->", lastDay.toString())

            dateValue.text =
                "$newday/$newmonth/$newVal"

        } else {
            dateValue.text =
                dayPicker.value.toString() + "/" + monthPicker.value.toString() + "/" + yearPicker.value.toString()
        }
    }

}