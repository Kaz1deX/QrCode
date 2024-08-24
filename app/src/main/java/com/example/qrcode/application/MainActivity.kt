package com.example.qrcode.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.qrcode.navigation.Navigation
import com.example.qrcode.ui.theme.QrCodeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QrCodeTheme {
                val navController = rememberNavController()

                Navigation(
                    navController = navController,
                    activityContext = this
                )
            }
        }
    }
}