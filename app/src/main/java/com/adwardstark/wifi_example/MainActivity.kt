package com.adwardstark.wifi_example

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import com.adwardstark.wifi_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var viewBinder: ActivityMainBinding

    private val permissionLauncher =
        registerForActivityResult(RequestPermission()) { isGranted: Boolean ->
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
    }

    override fun onResume() {
        super.onResume()
        checkRuntimePermission()
    }

    private fun checkRuntimePermission() {
        if(!hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            showToast("Permission already granted")
        }
    }

    private fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun Context.hasPermission(permission: String): Boolean {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
    }
}