package com.coronatracker.coronatracker.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.coronatracker.coronatracker.R
import com.coronatracker.coronatracker.baseclass.BaseActivity
import com.coronatracker.coronatracker.databinding.SettingActivityBinding
import com.coronatracker.coronatracker.utils.PrefManager

class SettingActivity : BaseActivity<SettingActivityBinding>(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.setting_activity)
        initData()
    }

    private fun initData() {
        binding.imageViewBack.setOnClickListener(this)
        binding.buttonLogOut.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonLogOut -> {

                PrefManager.setValue(this, "isLogin", false)
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
            }
            R.id.imageViewBack -> {
                finish()
            }
        }
    }
}