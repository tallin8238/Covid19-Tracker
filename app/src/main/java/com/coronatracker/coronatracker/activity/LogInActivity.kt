package com.coronatracker.coronatracker.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.coronatracker.coronatracker.R
import com.coronatracker.coronatracker.baseclass.BaseActivity
import com.coronatracker.coronatracker.databinding.LogInActivityBinding
import com.coronatracker.coronatracker.model.User
import com.coronatracker.coronatracker.utils.CommonFunction.checkForStringISEmpty
import com.coronatracker.coronatracker.utils.PrefManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*


class LogInActivity : BaseActivity<LogInActivityBinding>(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.log_in_activity)
        initData()
    }

    private fun initData() {

        auth = FirebaseAuth.getInstance()

        binding.imageViewBack.setOnClickListener(this)
        binding.buttonLogIn.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonLogIn -> {
                checkSignUp()

            }
            R.id.imageViewBack -> {
                finish()
            }
        }
    }

    private fun checkSignUp() {
        if (!validate()) {
            auth.signInAnonymously()
                .addOnCompleteListener { task: Task<AuthResult?> ->
                    if (task.isSuccessful) {
                        val myRef = getDatabaseRef().child(
                            "Users/" +
                                    FirebaseAuth.getInstance().currentUser
                                    !!.uid
                        )

                        // Read from the database
                        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                if (dataSnapshot.value != null) {
                                    val value: User =
                                        dataSnapshot.getValue<User>(User::class.java)!!
                                    PrefManager.setValue(
                                        this@LogInActivity,
                                        "UserName",
                                        value.getName()
                                    )
                                    showCustomNotification("Login Successfully")
                                    saveAuthUserData()
                                } else {
                                    PrefManager.deleteCache(this@LogInActivity)
                                    PrefManager.deleteAll(this@LogInActivity)
                                    showCustomNotification(
                                        "There is not record corresponding to this identifier."
                                    )
                                }
                                dismissLoader()
                            }

                            override fun onCancelled(error: DatabaseError) {
                                // Failed to read value
                            }
                        })
                    } else {
                        dismissLoader()
                        showCustomNotification(
                            Objects.requireNonNull(task.exception).toString()
                        )
                    }
                }
        }
    }


    private fun saveAuthUserData() {

        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun validate(): Boolean {
        return checkForStringISEmpty("Please enter Phone No.", binding.inputLayoutPhoneNo)
    }

}