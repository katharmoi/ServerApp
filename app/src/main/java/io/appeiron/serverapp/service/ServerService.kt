package io.appeiron.serverapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import android.os.RemoteException
import android.util.Log
import io.appeiron.serverapp.IResponseCallback
import io.appeiron.serverapp.ISampleApi

// Sample Server class
class ServerService : Service() {

    override fun onBind(intent: Intent?): IBinder {
        return ServerBinder()
    }

    inner class ServerBinder : ISampleApi.Stub() {
        override fun respond(cb: IResponseCallback?) {
            ServerThread(cb).start()
        }
    }

    inner class ServerThread(private val cb: IResponseCallback?) : Thread() {
        override fun run() {
            sleep(5000)
            try {
                cb?.onSuccess(Process.myPid().toString())
            } catch (e: RemoteException) {
                Log.e("SampleJob", "Exception when calling onSuccess()", e)
            }

        }
    }

}