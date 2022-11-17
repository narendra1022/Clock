package com.example.clock

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Vibrator
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService

class AlarmReciever: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val i =Intent(context,destination::class.java)

        intent!!.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent=PendingIntent.getActivity(context,0,i,0)

        val builder=NotificationCompat.Builder(context!!,"narendra")
            .setSmallIcon(R.drawable.alarm)
            .setContentTitle("Alarm !!")
            .setContentText("Alarm is ringing")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
        val notificationCompat =NotificationManagerCompat.from(context)
        notificationCompat.notify(123,builder.build())


        val vibrator =context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(4000)
        Toast.makeText(context,"Alarm Reminder !!",Toast.LENGTH_SHORT).show()

        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        if(alarmUri==null){
            alarmUri =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }

        var ringtone = RingtoneManager.getRingtone(context,alarmUri)
        ringtone.play()

    }
}