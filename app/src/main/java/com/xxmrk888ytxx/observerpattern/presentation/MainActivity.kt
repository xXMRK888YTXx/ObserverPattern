package com.xxmrk888ytxx.observerpattern.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xxmrk888ytxx.observerpattern.UseCase.UpdateWeatherUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var updateWeatherUseCase: UpdateWeatherUseCase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "1"
            ) {
                composable("1") {
                    val viewModel = hiltViewModel<ScreenViewModel>()

                    val weather by viewModel.weather.collectAsState()

                    Screen(
                        screenNumber = 1,
                        onNext = { navController.navigate("2") },
                        weatherValue = weather,
                        onSubscribe = {
                            viewModel.subscribe()
                        },
                        onUnSubscribe = { viewModel.unSubscribe() }
                    )
                }

                composable("2") {
                    val viewModel = hiltViewModel<ScreenViewModel>()

                    val weather by viewModel.weather.collectAsState()

                    Screen(
                        screenNumber = 2,
                        onNext = { navController.navigate("3") },
                        weatherValue = weather,
                        onSubscribe = {
                            viewModel.subscribe()
                        },
                        onUnSubscribe = { viewModel.unSubscribe() }
                    )
                }

                composable("3") {
                    val viewModel = hiltViewModel<ScreenViewModel>()

                    val weather by viewModel.weather.collectAsState()

                    Screen(
                        screenNumber = 3,
                        onNext = {  },
                        weatherValue = weather,
                        onSubscribe = {
                            viewModel.subscribe()
                        },
                        onUnSubscribe = { viewModel.unSubscribe() }
                    )
                }

            }
        }
    }


    override fun onResume() {
        super.onResume()

        lifecycleScope.launch {
            while (isActive) {
                updateWeatherUseCase.execute()
                delay(5000)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        lifecycleScope.coroutineContext.cancelChildren()
    }
}