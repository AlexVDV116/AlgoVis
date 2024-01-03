package com.example.algovis.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovis.algorithms.InsertionSort
import com.example.algovis.events.AppEvents
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlgorithmViewModel (
    private val insertionSort: InsertionSort
) : ViewModel() {

    var arr = mutableStateOf(
        intArrayOf(
            100, 120, 80, 55, 40, 5, 25, 320, 80, 23, 534, 64
        )
    )

    val isPlaying = mutableStateOf(false)
    val onSortingFinish = mutableStateOf(false)
    private var delay = 150L
    private var pause = false

    private var next = 1
    private var previous = 0

    private var sortedArrayLevels = mutableListOf<List<Int>>()

    init {
        viewModelScope.launch {
            insertionSort.sort(
                arr.value.clone()
            ) { modifiedArray ->
                sortedArrayLevels.add(modifiedArray.toMutableList())
            }
        }
    }

    fun onEvent(event: AppEvents) {
        when(event) {
            is AppEvents.PlayPause -> {
                playPauseAlgorithm()
            }
            is AppEvents.SlowDown -> {
                slowDown()
            }
            is AppEvents.SpeedUp -> {
                speedUp()
            }
            is AppEvents.Previous -> {
                previous()
            }
            is AppEvents.Next -> {
                next()
            }
        }
    }

    private fun next() {
        if (next < sortedArrayLevels.size) {
            arr.value = sortedArrayLevels[next].toIntArray()
            next++
            previous++
        }
    }

    private fun previous() {
        if (previous >= 0 ) {
            arr.value = sortedArrayLevels[previous].toIntArray()
            next--
            previous--
        }
    }

    private fun speedUp() {
        delay -= 100
    }

    private fun slowDown() {
        if (delay >= 100) {
            delay -= 100
        }
    }

    private fun playPauseAlgorithm() {
        if (isPlaying.value) {
            pause()
        } else {
            play()
            isPlaying.value = !isPlaying.value
        }
    }

    private var sortingState = 0
    private fun play() = viewModelScope.launch {
        pause = false
        for (i in sortingState until sortedArrayLevels.size) {
            if (!pause) {
                delay(delay)
                arr.value = sortedArrayLevels[i].toIntArray()
            } else {
                sortingState = i
                next = i + 1
                previous = i
                return@launch
            }
        }
        onSortingFinish.value = true
    }

    private fun pause() {
        pause = true
    }
}