package com.example.clock

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.roundToInt

class Timer : AppCompatActivity() {

    private lateinit var start:FloatingActionButton
    private lateinit var stop:FloatingActionButton
    private lateinit var reset:FloatingActionButton
    private lateinit var timetv:TextView
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)


        val intent = Intent(this, Mybackgroundservice::class.java)
        start.setOnClickListener {
            Start()
        }

       stop.setOnClickListener {
            stop()
        }
        reset.setOnClickListener {
            reset()
        }
        serviceIntent = Intent(applicationContext, Mybackgroundservice::class.java)
        registerReceiver(updatetime, IntentFilter(Mybackgroundservice.UPDATED_TIME))

    }

    private fun reset() {
        stop()
        time = 0.0
        timetv.text = getformettime(time)
    }

    private fun stop() {
        stopService(serviceIntent)
    }



    private fun Start() {
        serviceIntent.putExtra(Mybackgroundservice.CURRENT_TIME, time)
        startService(serviceIntent)
    }

    private val updatetime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val time = intent.getDoubleExtra(Mybackgroundservice.CURRENT_TIME, 0.0)
            timetv.text = getformettime(time)
        }
    }

    private fun getformettime(time: Double): String {
        val timeInt = time.roundToInt()
        val hours = timeInt % 86400 / 3600
        val min = timeInt % 86400 % 3600 / 60
        val sec = timeInt  % 86400 % 3600 % 60
        return String.format("%02d:%02d:%02d", hours, min, sec)

    }
}