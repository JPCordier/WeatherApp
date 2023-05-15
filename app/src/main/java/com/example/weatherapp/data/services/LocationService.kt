package com.example.weatherapp.services



import android.Manifest
import android.app.Activity
import android.app.Service
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.weatherapp.event.LocationChangedEvent
import org.greenrobot.eventbus.EventBus

class LocationService(private val context: Activity) : Service(), LocationListener,
    ActivityCompat.OnRequestPermissionsResultCallback , ILocationService {
    private var mLocation: Location? = null
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onLocationChanged(location: Location) {
        mLocation = location
        EventBus.getDefault().post(LocationChangedEvent(location))
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == MY_PERMISSIONS_ACCESS_FINE_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocationUpdates()
            }
        }
    }

    override val location: Location?
        get() {
            if (mLocation == null) {
                start()
            }
            return mLocation
        }

    override fun start() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission()
        } else {
            requestLocationUpdates()
        }
    }

    override fun stop() {
        if (locationManager != null) locationManager!!.removeUpdates(this)
    }

    private fun requestPermissionInternal() {
        ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            MY_PERMISSIONS_ACCESS_FINE_LOCATION)
    }

    private fun requestPermission() {
          requestPermissionInternal()

    }

    private fun requestLocationUpdates() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (locationManager == null)
                locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
            if (locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, SCHEDULED_INTERVAL.toLong(), METER_DIFFERENCE_THRESHOLD.toFloat(), this)
                val providers = locationManager!!.getProviders(true)
                for (provider in providers) {
                    val location = locationManager!!.getLastKnownLocation(provider)
                        ?: continue
                    if (mLocation == null || location.accuracy < mLocation!!.accuracy) {
                        // Found best last known location: %s", l);
                        mLocation = location
                    }
                }
            } else if (locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, SCHEDULED_INTERVAL.toLong(), METER_DIFFERENCE_THRESHOLD.toFloat(), this)
                mLocation = locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            }
        } else Log.d(TAG, "'RequestLocationUpdates' called without required permission")
    }


    companion object {
        val TAG: String = LocationService::class.java.simpleName
        const val MY_PERMISSIONS_ACCESS_FINE_LOCATION = 197
        private const val METER_DIFFERENCE_THRESHOLD = 200 //meters
        private const val SCHEDULED_INTERVAL = 1 //minutes
        private var locationManager: LocationManager? = null
    }
}