package com.example.simplecryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.simplecryptoapp.ui.theme.SimpleCryptoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCryptoAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    //
                }
            }
        }
    }
}