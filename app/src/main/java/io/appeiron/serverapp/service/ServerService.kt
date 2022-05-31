package io.appeiron.serverapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder


class ServerService : Service() {

//    private val binder = object : ISampleApi.Stub() {
//
//
//    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}