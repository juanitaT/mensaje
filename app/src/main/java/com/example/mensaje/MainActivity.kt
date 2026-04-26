package com.example.mensaje

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.mensaje.ui.theme.FMCScreen
import com.example.mensaje.ui.theme.MensajeTheme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        Log.d(TAG, "Permiso de notificaciones: $isGranted")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askNotificationPermission()
        enableEdgeToEdge()
        setContent {
            MensajeTheme {
                FMCScreen()
            }
        }
    }


private fun askNotificationPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }
}
}