package com.xxmrk888ytxx.observerpattern.domain

interface WeatherObserver {

    fun addListener(listener: WeatherListener)

    fun removeListener(listener: WeatherListener)

    fun notify(value:Int)
}