package com.coronatracker.coronatracker.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.coronatracker.coronatracker.utils.Constants
import com.coronatracker.coronatracker.utils.Constants.entryTime
import com.coronatracker.coronatracker.utils.PrefManager
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SimpleScannerActivity : Activity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null
    private val TAG = "SimpleScannerActivity"
    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this) // Programmatically initialize the scanner view
        setContentView(mScannerView) // Set the scanner view as the content view
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera() // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera() // Stop camera on pause
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun handleResult(rawResult: Result) {
        Log.e(TAG, rawResult.text)
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern(Constants.dateFormat1)
        val formatted = current.format(formatter)
        entryTime = System.currentTimeMillis().toDouble()
        PrefManager.setValue(this, "LocationName", rawResult.text)
        PrefManager.setValue(this, "CurrentDate", formatted)
        val intent = Intent(this, CurrentStatusActivity::class.java)
        startActivity(intent)
    }
}