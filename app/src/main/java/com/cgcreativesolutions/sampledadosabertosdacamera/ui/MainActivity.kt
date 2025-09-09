package com.cgcreativesolutions.sampledadosabertosdacamera.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.HomeScreen
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.screens.home.HomeViewModel
import com.cgcreativesolutions.sampledadosabertosdacamera.ui.theme.SampleDadosAbertosDaCameraTheme

class MainActivity : ComponentActivity() {
    
    val viewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleDadosAbertosDaCameraTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black),
                ) { innerPadding ->
                    val state by viewModel.state.collectAsState()
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onEvent = { event -> viewModel.onEvent(event) }
                    )
                }
            }
        }
    }
}
