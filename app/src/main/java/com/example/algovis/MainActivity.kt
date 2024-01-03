package com.example.algovis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.algovis.algorithms.InsertionSort
import com.example.algovis.events.AppEvents
import com.example.algovis.ui.theme.AlgoVisTheme
import com.example.algovis.viewmodel.AlgorithmViewModel
import com.example.algovis.viewmodel.AlgorithmViewModelProvider

class MainActivity : ComponentActivity() {
    // Dagger Hilt - ViewModelProviderFactory
    private val viewModel: AlgorithmViewModel by lazy {
        val viewModelProviderFactory = AlgorithmViewModelProvider(InsertionSort())
        ViewModelProvider(this, viewModelProviderFactory)[AlgorithmViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlgoVisTheme {
            
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column {
                        VisualizerSection(arr = viewModel.arr.value,
                        modifier = Modifier.fillMaxWidth()
                        )

                        val isPlaying = viewModel.isPlaying.value
                        val isFinished = viewModel.onSortingFinish.value

                        BottomBar(
                            onPlayPauseClick = { viewModel.onEvent(AppEvents.PlayPause) },
                            onNextStepClick = { viewModel.onEvent(AppEvents.Next) },
                            onBackStepClick = { viewModel.onEvent(AppEvents.Previous) },
                            onSpeedUpClick = { viewModel.onEvent(AppEvents.SpeedUp) },
                            onSlowDownClick = { viewModel.onEvent(AppEvents.SlowDown) },
                            modifier = Modifier.fillMaxWidth()
                                .height(75.dp),
                            isPlaying = if (isFinished) !isFinished else isPlaying
                        )
                    }
                }
            }
        }
    }
}