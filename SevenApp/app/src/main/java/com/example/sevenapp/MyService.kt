package com.example.sevenapp

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*

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
//        var delay = 5000L
//        GlobalScope.launch {
//            while (delay < 200000)
//            delay(2000L)

//            delay+=2000
//        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
//        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show()

    }
    
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//        do nothing
    }

    override fun onSensorChanged(event: SensorEvent) {

        ax = event.values[0].toString()
        ay = event.values[1].toString()
        az = event.values[2].toString()
//        Toast.makeText(this@MyService, ax.toString()+ay.toString()+az.toString(), Toast.LENGTH_SHORT).show()
        Log.d("sensor","$ax $ay $az")
    }
}
