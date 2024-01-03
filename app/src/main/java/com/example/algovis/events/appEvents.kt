package com.example.algovis.events

sealed class AppEvents {
    object SlowDown: AppEvents()
    object PlayPause: AppEvents()
    object SpeedUp: AppEvents()
    object Previous: AppEvents()
    object Next: AppEvents()
}
