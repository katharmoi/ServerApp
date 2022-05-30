package io.appeiron.serverapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.appeiron.serverapp.databinding.ActivityMainBinding
import io.appeiron.serverapp.domain.model.LatestClient

class MainActivity : AppCompatActivity() {

    //Main Layout Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val client = LatestClient.client

        binding.txtPackage.text = client?.clientPackageName
        binding.txtPid.text = client?.clientProcessId
    }
}