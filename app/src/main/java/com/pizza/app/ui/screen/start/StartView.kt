package com.pizza.app.ui.screen.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pizza.app.R
import com.pizza.app.ui.navigation.Screen
import com.pizza.app.ui.theme.DarkBlue
import com.pizza.app.utils.BackPressHandler
import com.pizza.app.utils.Const
import kotlinx.coroutines.delay

@Composable
fun StartView(
    navController: NavController
) {

    Countdown(1000L) {
        navController.navigate(Screen.Login.name)
    }

    UI()

    BackPressHandler{}
}

@Composable
private fun UI() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(DarkBlue)
    ) {
        Image(
            painter = painterResource(id = R.drawable.full_logo),
            contentDescription = Const.IMAGE_DESCRIPTION,
            modifier = Modifier.size(300.dp)
        )
    }
}

@Composable
fun Countdown(targetTime: Long, endEvent: () -> Unit) {
    var remainingTime by remember(targetTime) {
        mutableLongStateOf(targetTime - System.currentTimeMillis())
    }

    LaunchedEffect(remainingTime) {
        delay(targetTime)
        remainingTime = targetTime - System.currentTimeMillis()
        endEvent()
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    UI()
}