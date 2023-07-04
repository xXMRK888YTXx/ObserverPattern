package com.xxmrk888ytxx.observerpattern.presentation

import android.accessibilityservice.AccessibilityService.ScreenshotResult
import android.telephony.euicc.DownloadableSubscription
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Screen(
    screenNumber:Int,
    onNext:() -> Unit,
    weatherValue:Int,
    onSubscribe:() -> Unit,
    onUnSubscribe:() -> Unit
) {

    LaunchedEffect(key1 = Unit, block = {
        onSubscribe()
    })

    DisposableEffect(key1 = Unit, effect = {
        this.onDispose {
            onUnSubscribe()
        }
    })

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current Screen:$screenNumber")
        Text(text = "Weather value:$weatherValue")

        Button(onClick = onNext) {
            Text(text = "Next")
        }
    }
}