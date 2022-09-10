package com.example.sevenonkotlin

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log

class MyService : Service(), SensorEventListener {

    companion object {
        var ax: String? = null
        var ay: String? = null
        var az: String? = null
    }

    private lateinit var sensorManager: SensorManager

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(
            Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }
    
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//        do nothing
    }

    override fun onSensorChanged(event: SensorEvent) {

        ax = event.values[0].toString()
        ay = event.values[1].toString()
        az = event.values[2].toString()
//        Log.d("sensor","$ax $ay $az")
    }
}
