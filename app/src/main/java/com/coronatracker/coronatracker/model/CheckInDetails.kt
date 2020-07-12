package com.coronatracker.coronatracker.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.coronatracker.coronatracker.utils.CommonFunction.getDateTimeFromMilliseconds
import com.coronatracker.coronatracker.utils.Constants.dateFormatDatabase

class CheckInDetails {

    private var checkInTime = 0.0
    private var checkOutTime = 0.0
    private var placeName = ""
    private var userName = ""
    private var uuid=""
    private var checkInDate = ""

    fun getCheckInTime(): Double {
        return checkInTime
    }

    fun setCheckInTime(checkInTimeValue: Double) {
        this.checkInTime = checkInTimeValue
    }

    fun getCheckOutTime(): Double {
        return checkOutTime
    }

    fun setCheckOutTime(checkOutTimeValue: Double) {
        this.checkOutTime = checkOutTimeValue
    }

    fun getPlaceName(): String {
        return placeName
    }

    fun setPlaceName(placeNameValue: String) {
        this.placeName = placeNameValue
    }

    fun getUserName(): String {
        return userName
    }

    fun setUserName(userNameValue: String) {
        this.userName = userNameValue
    }
    fun getUuid(): String {
        return uuid
    }

    fun setUuid(uuidValue: String) {
        this.uuid = uuidValue
    }
    fun getCheckInDate(): String {
        return checkInDate
    }

    fun setCheckInDate(checkInDateValue: String) {
        this.checkInDate = checkInDateValue
    }

}