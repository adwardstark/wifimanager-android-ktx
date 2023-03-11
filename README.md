WifiManager-Android-KTX
=======================
[![Release](https://jitpack.io/v/adwardstark/wifimanager-android-ktx.svg)](https://jitpack.io/#adwardstark/wifimanager-android-ktx)

To cut down on boilerplate code and streamline several frequently used APIs, this lightweight library offers extension functions to the Android Wifi-Manager API that is currently accessible. Moreover, it employs Kotlin-coroutines to eliminate reliance on callbacks and broadcast receivers.

## Compatibility
----------------
WifiManager-Android-KTX requires Android [Jellybean](https://developer.android.com/about/versions/jelly-bean) (API level 16) or higher.

## Installation
---------
 * **Step 1.** Add the JitPack repository to your root `build.gradle` file.
    ```gradle
    allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }
    ```

 * **Step 2.** Add the following dependency to your module `build.gradle` file.
    ```gradle
    dependencies {
      implementation 'com.github.adwardstark:wifimanager-android-ktx:{latest_version}'
    }
    ```

## Available Extension Functions
-----------------------------------

These are the extensions that are available within this library:

 * **turnOnWifi()** : Turn-on wifi on device prior to [ `Android-Q` ] and shows system-dialog on Q and above.
 * **turnOffWifi()** : Turn-off wifi on device prior to [ `Android-Q` ] and shows system-dialog on Q and above.
 * **isWifiEnabled()** : Returns true if wifi is enabled on device.
 * **isConnectedToWifi()** : Returns true if the device is connected to a wifi-network.
 * **isConnectedToWifiOf(networkSSID: String)**: Returns true if the device is connected to provided SSID.
 * **wifiManager()** : Returns the wifi-manager instance or null.
 * **wifiConnectionInfo()** : Returns connection-information of current wifi-network or null.
 * **getWifiInfoOf(networkSSID: String)** : Returns connection-information of provided SSID or null.
 * **getSSIDOfConnectedWifi()** : Returns SSID of current wifi-network or null.
 * **getIPAddressOfConnectedWifi()** : Returns IP-Address of connected wifi-network or null.
 * **getIPAddressFrom(info: WifiInfo)** : Returns IP-Address from provided connection-information or null.
 * **getWifiScanResults()** : Returns list of wifi-networks if available, uses [ `suspendCancellableCoroutine{}` ].

## License
-------------
Apache 2.0. See the [LICENSE](https://github.com/adwardstark/wifimanager-android-ktx/blob/master/LICENSE) file for details.