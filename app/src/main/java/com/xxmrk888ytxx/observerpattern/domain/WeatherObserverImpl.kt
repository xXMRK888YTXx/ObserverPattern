package com.xxmrk888ytxx.observerpattern.domain

import android.util.Log
import javax.inject.Inject

class WeatherObserverImpl @Inject constructor() : WeatherObserver {

    private val listeners by lazy { mutableSetOf<WeatherListener>() }

    @Volatile
    private var lastValue = 0

    override fun addListener(listener: WeatherListener) {
        listeners.add(listener)

        listener.onWeatherChange(lastValue)

        Log.d("def",listeners.size.toString())
    }

    override fun removeListener(listener: WeatherListener) {
        listeners.remove(listener)

        Log.d("def",listeners.size.toString())
    }

    override fun notify(value: Int) {
        val copy = listeners

        lastValue = value

        copy.forEach {
            it.onWeatherChange(value)
        }

        Log.d("def","notified")
    }
}