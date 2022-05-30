package io.appeiron.serverapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import io.appeiron.serverapp.ISampleApi

class ServerService : Service() {

    private val binder = object : ISampleApi.Stub() {

        override fun getPid(): Int = Process.myPid()

    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
}