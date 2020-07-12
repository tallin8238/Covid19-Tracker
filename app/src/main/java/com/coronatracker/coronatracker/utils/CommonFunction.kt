package com.coronatracker.coronatracker.utils

import com.coronatracker.coronatracker.utils.Constants.dateFormatDatabase
import com.coronatracker.coronatracker.utils.Constants.timeFormat
import com.google.android.material.textfield.TextInputLayout
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object CommonFunction {

    var timeDiff: Long = 0

    fun centimeterToFT(cm: Int): String {
        val ft = 0.0328 * cm
        val str = String.format(Locale.ENGLISH, "%.2f", ft)
        val strValue: String
        strValue = if (str.contains(".")) {
            val strArray = str.split("\\.".toRegex()).toTypedArray()
            val str1 = strArray[0] + "'"
            val str2 = strArray[1]
            val doubleConvert = checkForIntegerParse(str2)
            "$str1$doubleConvert''"
            // strValue = str1 + str2 + "''";
        } else {
            val strArray = str.split("\\.".toRegex()).toTypedArray()
            strArray[0] + "'"
        }
        return strValue
    }

    fun checkForIntegerParse(value: String): Int {
        return if (value != "") value.toInt() else 0
    }

    fun stringFormat(str: Double): String? {
        return String.format(Locale.ENGLISH, "%.2f", str)
    }


    fun generateUUID(): String {
        return UUID.randomUUID().toString()
    }

    fun formatDate(
        input: String?,
        output: String?,
        date: String?
    ): String {
        val inputFormat: DateFormat =
            SimpleDateFormat(input, Locale.ENGLISH)
        val outputFormat: DateFormat =
            SimpleDateFormat(output, Locale.ENGLISH)
        var date1: Date? = null
        try {
            date1 = inputFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputFormat.format(Objects.requireNonNull(date1))
    }

    fun getCurrentDate(): String {
        val displayFormat =
            SimpleDateFormat(Constants.dateFormat, Locale.ENGLISH)
        val date = GregorianCalendar()
        date.time = Date()
        val currentDate = date.time
        return displayFormat.format(currentDate)
    }

    fun getCurrentTime(): String {
        val currentLocalTime = Calendar.getInstance().time
        val date: DateFormat = SimpleDateFormat(timeFormat, Locale.ENGLISH)

        return date.format(currentLocalTime)
    }

    fun getMonth(month: Int): String {
        return DateFormatSymbols().months[month - 1]
    }

    fun getMillisecond(dateTime: String, currentDateFormat: String): Long {
        if (dateTime.isNotEmpty()) {
            val sdf = SimpleDateFormat(currentDateFormat, Locale.ENGLISH)
            val date = sdf.parse(dateTime)
            return date.time
        }
        return 0
    }

    fun getStringToDate(
        dateTime: String,
        currentDateFormat: String,
        outPutDateFormat: String
    ): String {
        return getDateTimeFromMilliseconds(
            getMillisecond(dateTime, currentDateFormat),
            outPutDateFormat
        )
    }

    fun getDateTimeFromMilliseconds(millis: Long, desiredDateTimeFormat: String): String {
        val sdf = SimpleDateFormat(desiredDateTimeFormat, Locale.ENGLISH)
        val date = Date(millis)
        return sdf.format(date)
    }

    fun changeDateFormat(
        dateToConvert: String,
        currentDateFormat: String,
        desiredDateFormat: String
    ): String {
        if (dateToConvert.isNotEmpty()) {
            val formatter = SimpleDateFormat(currentDateFormat, Locale.ENGLISH)
            val date = formatter.parse(dateToConvert) as Date
            val newFormat = SimpleDateFormat(desiredDateFormat, Locale.ENGLISH)
            return newFormat.format(date)
        }
        return ""
    }
    fun checkForStringISEmpty(
        message: String,
        textInputLayout: TextInputLayout
    ): Boolean {
        return if (Objects.requireNonNull(textInputLayout.editText)?.text
                .toString().isEmpty()
        ) {
            Objects.requireNonNull(textInputLayout).error = message
            true
        } else {
            Objects.requireNonNull(textInputLayout).error = null
            false
        }
    }
}