package com.coronatracker.coronatracker.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.coronatracker.coronatracker.R
import com.coronatracker.coronatracker.baseclass.BaseActivity
import com.coronatracker.coronatracker.databinding.VerifyActivityBinding
import com.coronatracker.coronatracker.utils.PrefManager
import kotlinx.android.synthetic.main.verify_activity.*

class VerifyActivity : BaseActivity<VerifyActivityBinding>(), View.OnClickListener {
    private var verify =0
    private var check = "1428"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.verify_activity)
        initData()
    }

    private fun initData() {
        binding.imageViewBack.setOnClickListener(this)
        binding.buttonEnter.setOnClickListener(this)
        binding.editView1.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                verify+=editView1.text.toString().toInt()
                true
            } else {
                false
            }
        }
        binding.editView2.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                verify+=editView2.text.toString().toInt()
                true
            } else {
                false
            }
        }
        binding.editView3.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                verify+=editView3.text.toString().toInt()
                true
            } else {
                false
            }
        }
        binding.editView4.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                verify+=editView4.text.toString().toInt()
                true
            } else {
                false
            }
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonEnter -> {
             //   if (verify == check) {
                    PrefManager.setValue(this, "isLogin", true)
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                /*} else {
                    showCustomNotification("Please check verify number")
                }*/

            }
            R.id.imageViewBack -> {
                finish()
            }
        }
    }

}