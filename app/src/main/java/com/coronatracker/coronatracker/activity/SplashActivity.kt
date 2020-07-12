package com.coronatracker.coronatracker.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.coronatracker.coronatracker.R
import com.coronatracker.coronatracker.baseclass.BaseActivity
import com.coronatracker.coronatracker.databinding.SplashActivityBinding
import com.google.firebase.FirebaseApp
import com.coronatracker.coronatracker.utils.PrefManager

open class SplashActivity : BaseActivity<SplashActivityBinding>() {

    private var timeDuration = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindView(R.layout.splash_activity)

        doSplash()

    }

    private fun doSplash() {
        Handler().postDelayed({
            FirebaseApp.initializeApp(this)
            if (PrefManager.getValue(this@SplashActivity, "isLogin", false)) {
                val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this@SplashActivity, IntroSliderActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, timeDuration.toLong())
    }

}
