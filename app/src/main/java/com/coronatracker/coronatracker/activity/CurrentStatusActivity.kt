package com.coronatracker.coronatracker.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.coronatracker.coronatracker.R
import com.coronatracker.coronatracker.baseclass.BaseActivity
import com.coronatracker.coronatracker.databinding.CurrentStatusLayoutBinding
import com.coronatracker.coronatracker.model.CheckInDetails
import com.coronatracker.coronatracker.utils.CommonFunction
import com.coronatracker.coronatracker.utils.CommonFunction.getDateTimeFromMilliseconds
import com.coronatracker.coronatracker.utils.Constants
import com.coronatracker.coronatracker.utils.Constants.timeFormat
import com.coronatracker.coronatracker.utils.PrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class CurrentStatusActivity : BaseActivity<CurrentStatusLayoutBinding>(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.current_status_layout)
        initData()
    }

    private fun initData() {

        auth = FirebaseAuth.getInstance()
        binding.buttonCheckOut.setOnClickListener(this)
        binding.textViewSubDesc.text =
            "Check in at ".plus(
                PrefManager.getValue(this, "LocationName", "")
            ).plus(" at ")
                .plus(
                    getDateTimeFromMilliseconds(
                        Constants.entryTime.toLong(),
                        timeFormat
                    )
                )
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.buttonCheckOut -> {
                val mDatabase: DatabaseReference = getDatabaseRef().child("CheckIn")
                val userId = auth.currentUser!!.uid

                val checkIn = CheckInDetails()
                val uuid = CommonFunction.generateUUID()
                checkIn.setCheckInTime(Constants.entryTime)
                checkIn.setCheckOutTime(System.currentTimeMillis().toDouble())
                checkIn.setUserName(PrefManager.getValue(this, "UserName", ""))
                checkIn.setUuid(uuid)
                checkIn.setPlaceName(PrefManager.getValue(this, "LocationName", ""))
                checkIn.setCheckInDate(PrefManager.getValue(this, "CurrentDate", ""))

                mDatabase.child(userId).child(uuid).setValue(checkIn)
                PrefManager.deleteAll(this)
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

}