package com.adwardstark.wifi_example

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adwardstark.wifi_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinder: ActivityMainBinding

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
    }
}