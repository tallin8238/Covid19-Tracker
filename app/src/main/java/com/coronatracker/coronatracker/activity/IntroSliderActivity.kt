package com.coronatracker.coronatracker.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.coronatracker.coronatracker.R
import com.coronatracker.coronatracker.baseclass.BaseActivity
import com.coronatracker.coronatracker.databinding.IntroSliderActivityBinding

class IntroSliderActivity : BaseActivity<IntroSliderActivityBinding>(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.intro_slider_activity)
        initData()
    }

    private fun initData() {
        binding.buttonLetStart.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonLetStart -> {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }

        }
    }

}