package com.example.sevenonkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.example.sevenonkotlin.MyService.Companion.ax
import com.example.sevenonkotlin.MyService.Companion.ay
import com.example.sevenonkotlin.MyService.Companion.az
import com.example.sevenonkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var listOfXYZ = ArrayList<String>()
    private var list: List<String> = ArrayList()
    private var time = 0
    private var active = 0.0F
    private var pasive = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.text.movementMethod = ScrollingMovementMethod()

        GlobalScope.launch {
            while (time <= 60) {
                delay(60000L)
                startSensor(0)
                time++
            }
        }

    }
    private fun startSensor(entry: Int) {
        startService(entry)
        GlobalScope.launch {
            delay(1000L)
            var n = 0
            var pas = 0
            var act = 0
            listOfXYZ.clear()
            for (i in 0 until 10) {
                delay(500L)
                listOfXYZ.add("$ax,$ay,$az")
            }
            delay(500L)
            list = listOfXYZ[0].split(",")
            var x = list[0].toDouble()
            var y = list[1].toDouble()
            var z = list[2].toDouble()
            for (i in listOfXYZ) {
                list = i.split(",")
                if (round(x) in round(list[0].toDouble() - 1)..round(list[0].toDouble() + 1)
                    && round(y) in round(list[1].toDouble() - 1)..round(list[1].toDouble() + 1)
                    && round(z) in round(list[2].toDouble() - 1)..round(list[2].toDouble() + 1)
                ) {
                    pas++

                } else {
                    act++

                }
                x = list[0].toDouble()
                y = list[1].toDouble()
                z = list[2].toDouble()
            }

            if (pas > act) {
                pasive++
                n = 1
            } else if (act > pas) {
                if (entry == 0) {
                    startSensor(1)
                } else if (entry == 1) {
                    active++
                    n = 1
                }
            }
            if (n == 1) {
                stopService()
                showInfo()
            }
        }
    }

    private fun startService(entry: Int) {
        if (entry < 1) {
            startService(Intent(this, MyService::class.java))
        }
    }

    private fun stopService() {
        stopService(Intent(this, MyService::class.java))
    }

    private fun showInfo() {
        binding!!.pasive.text = String.format("%.2f", ((pasive * 100) / (pasive + active))) + "%"
        binding!!.active.text = String.format("%.2f", ((active * 100) / (pasive + active))) + "%"

    }
}