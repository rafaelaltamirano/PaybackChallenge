package com.example.paybackchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.paybackchallenge.ui.main.MainViewModel
import com.example.paybackchallenge.ui.router.Router
import com.example.paybackchallenge.ui.theme.PaybackChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComponent()
        }
    }
}

@Composable
fun MainComponent(mainModel: MainViewModel = viewModel()) {
    PaybackChallengeTheme {
        Surface(color = Color.White) {
             Router(mainModel)
        }
    }
}