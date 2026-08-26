package com.example.retrohardware

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.retrohardware.navigation.AppNavigation
import com.example.retrohardware.ui.theme.RetroHardwareTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            RetroHardwareTheme {

                AppNavigation()

            }
        }
    }
}