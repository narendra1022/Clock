package com.example.clock

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import androidx.core.content.getSystemService
import java.util.*
class MainActivity : AppCompatActivity() {

    var alarmManager: AlarmManager? = null
    var pendingIntent: PendingIntent? = null
    var alarmTimePicker: TimePicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        creanotificationchannel()

        alarmTimePicker = findViewById(R.id.timepicker)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
    }

    private fun creanotificationchannel() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val name:CharSequence = "Alarm"
                val discription = " Alarm Ringing "
                val importance= NotificationManager.IMPORTANCE_HIGH
                val channel= NotificationChannel("narendra",name,importance)
                channel.description=discription
                val notificationManager=getSystemService(
                    NotificationManager::class.java
                )
                notificationManager.createNotificationChannel(channel)
            }

    }

    fun onToggleClicked(view: View) {

        var time: Long

        if ((view as ToggleButton).isChecked) {
            Toast.makeText(this, "Alarm ON ", Toast.LENGTH_SHORT).show()

            var calendar = Calendar.getInstance()

            calendar[Calendar.HOUR_OF_DAY] = alarmTimePicker!!.currentHour
            calendar[Calendar.MINUTE] = alarmTimePicker!!.currentMinute

            val intent = Intent(this, AlarmReciever::class.java)

            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

            time = calendar.timeInMillis - calendar.timeInMillis % 60000
            if (System.currentTimeMillis() > time) {
                time = if (Calendar.AM_PM == 0) {
                    time + 1000 * 60 * 60 * 12
                } else {
                    time + 1000 * 60 * 60 * 24
                }
            } else {
                alarmManager!!.cancel(pendingIntent)
                Toast.makeText(this, "Alarm OFF ", Toast.LENGTH_SHORT).show()
            }
            alarmManager!!.setRepeating(
                AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,pendingIntent
            )

        }
    }
}