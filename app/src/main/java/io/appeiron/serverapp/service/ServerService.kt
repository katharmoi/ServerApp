package io.appeiron.serverapp.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import android.os.RemoteException
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import io.appeiron.serverapp.IResponseCallback
import io.appeiron.serverapp.ISampleApi

const val SERVER_MSG_INTENT = "server_msg_intent"
const val SERVER_MSG_KEY = "request_id"

// Sample Server class
class ServerService : Service() {

    override fun onBind(intent: Intent?): IBinder {
        return ServerBinder()
    }

    inner class ServerBinder : ISampleApi.Stub() {

        override fun getResponse(requestId: String?, cb: IResponseCallback?) {
            ServerThread(requestId, cb).start()
        }
    }

    inner class ServerThread(private val requestId: String?, private val cb: IResponseCallback?) :
        Thread() {
        override fun run() {
            sleep(5000)
            try {
                cb?.onSuccess(requestId, Process.myPid().toString())
                sendMessage(requestId)
            } catch (e: RemoteException) {
                Log.e("SampleJob", "Exception when calling onSuccess()", e)
            }

        }
    }

    /**
     * Sends LocalBroadcast msg for activity
     */
    private fun sendMessage(msg: String?) {
        val intent = Intent(SERVER_MSG_INTENT).apply {
            putExtra(SERVER_MSG_KEY, msg)
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

}