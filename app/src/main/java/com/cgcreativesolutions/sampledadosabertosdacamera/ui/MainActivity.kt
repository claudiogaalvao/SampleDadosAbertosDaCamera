package com.cgcreativesolutions.sampledadosabertosdacamera.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.app.App
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.HomeViewModel
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.theme.SampleDadosAbertosDaCameraTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleDadosAbertosDaCameraTheme {
                App()
            }
        }
    }
}
