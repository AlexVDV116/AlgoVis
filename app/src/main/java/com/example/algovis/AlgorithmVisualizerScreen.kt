package com.example.algovis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun VisualizerSection(
    modifier: Modifier = Modifier,
    arr: IntArray
) {
    BoxWithConstraints {
        val maxHeight = maxHeight - 75.dp
        val itemWidth = remember {
            maxWidth / arr.size - 8.dp
        }

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            arr.forEach {
                Box(
                    modifier = Modifier
                        .height(if (it.dp > maxHeight) maxHeight else it.dp)
                        .width(itemWidth)
                        .background(MaterialTheme.colorScheme.secondary)
                ) {

                }
            }
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onPlayPauseClick: () -> Unit,
    onNextStepClick: () -> Unit,
    onBackStepClick: () -> Unit,
    onSpeedUpClick: () -> Unit,
    onSlowDownClick: () -> Unit,
    isPlaying: Boolean = false
) {
    BottomAppBar(
        modifier = modifier
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            IconButton(onClick = onSlowDownClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_minus),
                    contentDescription = stringResource(id = R.string.slow_down),
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(16.dp)
                )
            }

            IconButton(onClick = onPlayPauseClick) {
                Icon(
                    painter = painterResource(
                        id = if (!isPlaying) R.drawable.play else R.drawable.pause
                    ),
                    contentDescription = stringResource(id = R.string.play_pause),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(onClick = onSpeedUpClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(onClick = onBackStepClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }

            IconButton(onClick = onNextStepClick) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(id = R.string.next),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}