package com.coronatracker.coronatracker.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.coronatracker.coronatracker.R
import com.coronatracker.coronatracker.adapter.LocationListAdapter
import com.coronatracker.coronatracker.baseclass.BaseActivity
import com.coronatracker.coronatracker.databinding.HomeActivityBinding
import com.coronatracker.coronatracker.model.CheckInDetails
import com.coronatracker.coronatracker.utils.CommonFunction.formatDate
import com.coronatracker.coronatracker.utils.CommonFunction.getCurrentDate
import com.coronatracker.coronatracker.utils.CommonFunction.getMonth
import com.coronatracker.coronatracker.utils.Constants.dateFormat
import com.coronatracker.coronatracker.utils.Constants.dateFormat1
import com.coronatracker.coronatracker.utils.Constants.dateFormat11
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.prolificinteractive.materialcalendarview.CalendarDay

class HomeActivity : BaseActivity<HomeActivityBinding>(), View.OnClickListener, PermissionListener {

    private var checkList = ArrayList<CheckInDetails?>()

    private var dateSelected = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.home_activity)
        initData()
    }

    private fun initData() {

        binding.imageViewSetting.setOnClickListener(this)
        binding.buttonLogEntry.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        dateSelected = formatDate(dateFormat, dateFormat1, getCurrentDate())
        getUserList(dateSelected)

        binding.calenderView.clearSelection()
        binding.calenderView.setDateSelected(CalendarDay.today(), true)
        binding.calenderView.setOnDateChangedListener { _, date, _ ->
            val dateValue = date.day
                .toString() + " " + getMonth(date.month + 1) + " " + date.year
            dateSelected = formatDate(
                dateFormat11, dateFormat1, dateValue
            )
            getUserList(dateSelected)
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imageViewSetting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
            R.id.buttonLogEntry -> {
                Dexter.withContext(this)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(this)
                    .check()

            }
        }
    }

    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

        val intent = Intent(this, SimpleScannerActivity::class.java)
        startActivity(intent)
    }

    override fun onPermissionRationaleShouldBeShown(p0: PermissionRequest?, p1: PermissionToken?) {

    }

    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {

    }

    private fun getUserList(dateSelected: String) {
        checkList.clear()
        val myRef = getDatabaseRef().child("CheckIn").child(
            FirebaseAuth.getInstance().currentUser!!.uid
        )
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                println(error.toString())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val children = snapshot.children
                children.forEach {
                    val value = it.getValue(CheckInDetails::class.java)

                    if (value!!.getCheckInDate() == dateSelected) {
                        checkList.add(it.getValue(CheckInDetails::class.java))
                    }
                }
                setAdapter()
            }
        })
    }

    private fun setAdapter() {
        val adapter = LocationListAdapter(this, checkList)
        binding.recyclerViewUser.adapter = adapter
    }

}