package io.appeiron.serverapp.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import io.appeiron.serverapp.databinding.ActivityMainBinding
import io.appeiron.serverapp.service.SERVER_MSG_INTENT
import io.appeiron.serverapp.service.SERVER_MSG_KEY


class MainActivity : AppCompatActivity() {

    //Main Layout Binding
    private lateinit var binding: ActivityMainBinding

    //Receiver for the service broadcasts
    lateinit var receiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Initialize Receiver
        receiver = object : BroadcastReceiver() {
            override fun onReceive(ctx: Context?, intent: Intent?) {
                val message = intent?.getStringExtra(SERVER_MSG_KEY)
                binding.txtRequestId.text = message
            }
        }
    }

    override fun onResume() {
        super.onResume()

        //Register Receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(
            receiver,
            IntentFilter(SERVER_MSG_INTENT)
        )
    }

    override fun onPause() {
        super.onPause()
        //Unregister the receiver
        unregisterReceiver(receiver)
    }
}