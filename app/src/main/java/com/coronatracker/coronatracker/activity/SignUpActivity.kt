package com.coronatracker.coronatracker.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.coronatracker.coronatracker.R
import com.coronatracker.coronatracker.baseclass.BaseActivity
import com.coronatracker.coronatracker.databinding.SignUpActivityBinding
import com.coronatracker.coronatracker.model.User
import com.coronatracker.coronatracker.utils.CommonFunction.checkForStringISEmpty
import com.coronatracker.coronatracker.utils.PrefManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import java.util.*


class SignUpActivity : BaseActivity<SignUpActivityBinding>(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    private var state = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.sign_up_activity)
        initData()
    }

    private fun initData() {

        auth = FirebaseAuth.getInstance()

        binding.imageViewBack.setOnClickListener(this)
        binding.buttonSignUp.setOnClickListener(this)
        binding.textViewLogin.setOnClickListener(this)

        val spinnerArray = resources.getStringArray(R.array.state_name)
        val spinnerArrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_spinner_item,
            spinnerArray
        )
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerState.adapter = spinnerArrayAdapter
        binding.spinnerState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                state = spinnerArray[position]
            }

        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonSignUp -> {
                checkSignUp()

            }
            R.id.imageViewBack -> {
                finish()
            }
            R.id.textViewLogin -> {
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun checkSignUp() {
        if (!validate()) {
            registerInFirebase()
        }
    }

    private fun registerInFirebase() {
        auth.signInAnonymously()
            .addOnCompleteListener(this@SignUpActivity) { task ->
                if (!task.isSuccessful) {
                    showCustomNotification(
                        Objects.requireNonNull<Any>(task.exception).toString()
                    )

                } else {
                    saveAuthUserData()
                }
            }
    }

    private fun saveAuthUserData() {
        val mDatabase: DatabaseReference = getDatabaseRef().child("Users")
        val userId = auth.currentUser!!.uid

        val user = User()
        PrefManager.setValue(this, "UserName", binding.inputLayoutName.editText?.text.toString())
        user.setName(binding.inputLayoutName.editText?.text.toString())
        user.setState(state)
        user.setCity(binding.inputLayoutCity.editText?.text.toString())
        user.setAadharNo(binding.inputLayoutAadharNo.editText?.text.toString())
        user.setContactNo(binding.inputLayoutPhoneNo.editText?.text.toString())

        mDatabase.child(userId).setValue(user)
        val intent = Intent(this, VerifyActivity::class.java)
        startActivity(intent)
    }

    private fun validate(): Boolean {
        return checkForStringISEmpty("Please enter name", binding.inputLayoutName) ||
                checkForStringISEmpty("Please enter city", binding.inputLayoutCity) ||
                checkForStringISEmpty("Please enter Phone No.", binding.inputLayoutPhoneNo) ||
                checkForStringISEmpty("Please enter Aadhar No.", binding.inputLayoutAadharNo)
    }

}