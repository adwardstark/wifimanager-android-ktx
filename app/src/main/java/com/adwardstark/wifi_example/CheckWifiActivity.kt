package com.adwardstark.wifi_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.adwardstark.wifi_example.databinding.ActivityCheckWifiBinding
import com.adwardstark.wifimanager_ktx.*

class CheckWifiActivity : AppCompatActivity() {

    private lateinit var viewBinder: ActivityCheckWifiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder = ActivityCheckWifiBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init()
    }

    private fun init() {
        viewBinder.verifyButton.setOnClickListener {
            val networkName: String = viewBinder.wifiNetworkNameText.text.trim().toString()
            if(isWifiEnabled()) {
                if(isConnectedToWifiOf(networkName)) {
                    printToConsole("Connected network matched.")
                } else {
                    printToConsole("Connected network didn't matched.")
                }
                getWifiInfoOf(networkName)?.let {
                    getIPAddressFrom(it)
                        ?.let { ipAddress ->
                        printToConsole("Found ssid: ${it.ssid}, ip-address: $ipAddress")
                    }
                }
            } else {
                AlertDialog.Builder(this)
                    .setTitle("WiFi turned-off")
                    .setMessage("Do you want to turn it on?")
                    .setPositiveButton("Yes") { _, _ ->
                        turnOnWifi()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .show()
            }
        }
    }

    private fun printToConsole(text: String) {
        runOnUiThread {
            var console = viewBinder.consoleText.text.toString()
            console += "\n> $text"
            viewBinder.consoleText.text = console
        }
    }
}