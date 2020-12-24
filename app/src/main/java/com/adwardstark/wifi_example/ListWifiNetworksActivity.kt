package com.adwardstark.wifi_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.adwardstark.wifi_example.databinding.ActivityListWifiNetworksBinding
import com.adwardstark.wifimanager_ktx.getWifiScanResults
import com.adwardstark.wifimanager_ktx.isWifiEnabled
import com.adwardstark.wifimanager_ktx.turnOnWifi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListWifiNetworksActivity : AppCompatActivity() {

    private lateinit var viewBinder: ActivityListWifiNetworksBinding

    private var isScanActive: Boolean = false
    private val wifiNetworksList: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinder = ActivityListWifiNetworksBinding.inflate(layoutInflater)
        setContentView(viewBinder.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init()
    }

    private fun init() {
        viewBinder.scanButton.setOnClickListener {
            if(!isScanActive) {
                startScan()
            } else {
                Toast.makeText(this, "Scan is already running", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun startScan() {
        if(isWifiEnabled()) {
            isScanActive = true
            viewBinder.scanMessage.text = getString(R.string.scanning_for_wifi_networks)
            GlobalScope.launch {
                val wifiScanResult = getWifiScanResults()
                if(wifiScanResult.isNotEmpty()) {
                    Log.i("startScan", "Found ${wifiScanResult.size} wifi-networks")
                    wifiNetworksList.clear()
                    wifiScanResult.forEach { result ->
                        wifiNetworksList.add(result.SSID)
                    }
                    addToList()
                } else {
                    Log.i("startScan", "No wifi-networks found")
                    setStoppedScanMessage()
                }
                isScanActive = false
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

    private fun addToList() {
        runOnUiThread {
            viewBinder.wifiList.adapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, wifiNetworksList.toArray())
            setStoppedScanMessage()
        }
    }

    private fun setStoppedScanMessage() {
        runOnUiThread {
            viewBinder.scanMessage.text = getString(R.string.scan_stopped)
        }
    }
}