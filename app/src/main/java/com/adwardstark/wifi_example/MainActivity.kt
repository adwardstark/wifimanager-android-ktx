package com.adwardstark.wifi_example

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.adwardstark.permissions_ktx.hasPermission
import com.adwardstark.permissions_ktx.singlePermissionResult
import com.adwardstark.wifi_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var viewBinder: ActivityMainBinding

    private val singlePermissionRequest = singlePermissionResult { isGranted ->
        if(isGranted) {
            Log.i(TAG, "Runtime permission granted")
            showToast("Permission granted")
        } else {
            Log.e(TAG, "Runtime permission denied")
            showToast("Permission denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init()
    }

    private fun init() {
        viewBinder.checkWifiButton.setOnClickListener {
            startActivity(Intent(this, CheckWifiActivity::class.java))
        }

        viewBinder.scanWifiButton.setOnClickListener {
            startActivity(Intent(this, ListWifiNetworksActivity::class.java))
        }

        viewBinder.checkPermissionButton.setOnClickListener {
            checkRuntimePermission()
        }

        checkRuntimePermission()
    }

    private fun checkRuntimePermission() {
        if(!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            singlePermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            showToast("Permission already granted")
        }
    }

    private fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}