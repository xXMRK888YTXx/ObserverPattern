package com.xxmrk888ytxx.observerpattern.DI

import com.xxmrk888ytxx.observerpattern.domain.WeatherObserver
import com.xxmrk888ytxx.observerpattern.domain.WeatherObserverImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun bindWeatherObserver(weatherObserverImpl: WeatherObserverImpl) : WeatherObserver
}