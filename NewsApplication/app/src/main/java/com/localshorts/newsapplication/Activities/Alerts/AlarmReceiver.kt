package com.localshorts.newsapplication.Activities.Alerts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val service = Intent(p0, NotificationService::class.java)
        service.putExtra("reason", p1!!.getStringExtra("reason"))
        service.putExtra("timestamp", p1!!.getLongExtra("timestamp", 0))

        p0!!.startService(service)
    }
}