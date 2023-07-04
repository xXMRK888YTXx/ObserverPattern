package com.xxmrk888ytxx.observerpattern.presentation

import androidx.lifecycle.ViewModel
import com.xxmrk888ytxx.observerpattern.domain.WeatherListener
import com.xxmrk888ytxx.observerpattern.domain.WeatherObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScreenViewModel @Inject constructor(
    private val weatherObserver: WeatherObserver
) : ViewModel() {

    fun subscribe() {
        weatherObserver.addListener(observer)
    }

    fun unSubscribe() {
        weatherObserver.removeListener(observer)
    }

    private val observer by lazy {
        object : WeatherListener {
            override fun onWeatherChange(value: Int) {
                _weatherValue.update { value }
            }

        }
    }

    private val _weatherValue = MutableStateFlow(0)

    val weather = _weatherValue.asStateFlow()


}