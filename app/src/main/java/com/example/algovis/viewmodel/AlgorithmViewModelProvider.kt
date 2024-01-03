package com.example.algovis.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.algovis.algorithms.InsertionSort

class AlgorithmViewModelProvider(
    private var insertionSort: InsertionSort
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlgorithmViewModel(insertionSort) as T
    }
}