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
    var lastDay = 0
    private val MONTH = arrayOf(
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
        val reg_layout = inflater.inflate(R.layout.dialog_year_picker, null)
        //val dateValue = reg_layout.findViewById<TextView>(R.id.dateValue)
        val birthday_dialog_title = reg_layout.findViewById<TextView>(R.id.birthday_dialog_title)
        dateValue = reg_layout.findViewById<TextView>(R.id.dateValue)
        dayPicker = reg_layout.findViewById<NumberPicker>(R.id.dayPicker)
        monthPicker = reg_layout.findViewById<NumberPicker>(R.id.monthPicker)
        yearPicker = reg_layout.findViewById<NumberPicker>(R.id.yearPicker)
        val btn_ok = reg_layout.findViewById<Button>(R.id.btn_ok)
        val btn_cancel = reg_layout.findViewById<Button>(R.id.btn_cancel)
        dialog.setView(reg_layout)
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
        monthPicker.displayedValues = MONTH

        //monthPicker.wrapSelectorWheel = false

        monthPicker.setOnValueChangedListener(this)


        dayPicker.minValue = 1
        dayPicker.maxValue = lastDay
        dayPicker.value = day
        //dayPicker.wrapSelectorWheel = false

        dayPicker.setOnValueChangedListener(this)

        var newday = ""
        var newmonth = ""

        if (day < 10) {
            newday = "0" + (day).toString()
        } else {
            newday = day.toString()
        }
        if (month < 10) {
            newmonth = "0" + (month).toString()
        } else {
            newmonth = month.toString()
        }

        dateValue.text = newday + "/" + newmonth + "/" + yearPicker.value.toString()


        btn_ok.setOnClickListener {
            //btnYear.setText(yearPicker.value.toString())
            //saveDate(yearPicker.value)
            // yearCode = yearPicker.value.toString()
            var newday = ""
            var newmonth = ""

            if (dayPicker.value < 10) {
                newday = "0" + (dayPicker.value).toString()
            } else {
                newday = dayPicker.value.toString()
            }
            if (monthPicker.value < 10) {
                newmonth = "0" + (monthPicker.value).toString()
            } else {
                newmonth = monthPicker.value.toString()
            }
            textView.setText(newday + "/" + newmonth + "/" + yearPicker.value.toString())

            alertDialog.dismiss()
        }

        btn_cancel.setOnClickListener { alertDialog.dismiss() }


        alertDialog.show()
    }

    @SuppressLint("SetTextI18n")
    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        if (dayPicker == picker) {
            var newday = ""
            if (newVal < 10) {
                newday = "0" + (newVal).toString()
            } else {
                newday = newVal.toString()
            }
            dateValue.text =
                newday + "/" + monthPicker.value.toString() + "/" + yearPicker.value.toString()
        } else if (monthPicker == picker) {
            var newmonth = ""
            var newday = ""

            if (newVal < 10) {
                newmonth = "0" + (newVal).toString()
            } else {
                newmonth = newVal.toString()
            }

            if (dayPicker.value < 10) {
                newday = "0" + (dayPicker.value).toString()
            } else {
                newday = dayPicker.value.toString()
            }

            val sdf = SimpleDateFormat("dd MM yyyy")
            val date: Date =
                sdf.parse(newday + " " + newmonth + " " + yearPicker.value.toString())// all done
            // all done
            val caln: Calendar = sdf.getCalendar()
            val value = caln.get(Calendar.MONTH)
            lastDay = caln.getActualMaximum(Calendar.DAY_OF_MONTH)
            dayPicker.minValue = 1
            dayPicker.maxValue = lastDay
            Log.d("value-->", lastDay.toString())
            dateValue.text =
                newday + "/" + newmonth + "/" + yearPicker.value.toString()
        } else if (yearPicker == picker) {
            var newmonth = ""
            var newday = ""

            if (monthPicker.value < 10) {
                newmonth = "0" + (monthPicker.value).toString()
            } else {
                newmonth = monthPicker.value.toString()
            }

            if (dayPicker.value < 10) {
                newday = "0" + (dayPicker.value).toString()
            } else {
                newday = dayPicker.value.toString()
            }

            val sdf = SimpleDateFormat("dd MM yyyy")
            val date: Date =
                sdf.parse(newday + " " + newmonth + " " + yearPicker.value.toString())// all done
            // all done
            val caln: Calendar = sdf.getCalendar()
            val value = caln.get(Calendar.MONTH)
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