package com.xxmrk888ytxx.observerpattern.UseCase

import com.xxmrk888ytxx.observerpattern.domain.WeatherObserver
import java.util.Date
import javax.inject.Inject

class UpdateWeatherUseCase @Inject constructor(
    private val weatherObserver: WeatherObserver
) {

    fun execute() {
        weatherObserver.notify((System.currentTimeMillis() % 35).toInt())
    }

}