package com.pizza.app.ui.navigation


import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInBack
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pizza.app.ui.screen.basket.BasketView
import com.pizza.app.ui.screen.history.HistoryView
import com.pizza.app.ui.screen.login.LoginView
import com.pizza.app.ui.screen.main.MainView
import com.pizza.app.ui.screen.main.MainViewModel
import com.pizza.app.ui.screen.registration.RegView
import com.pizza.app.ui.screen.start.StartView

enum class Screen {
    Login,
    Main,
    Registration,
    Start,
    Basket,
    History
}

@Composable
fun Navigation(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.Start.name,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    600, easing = EaseInCubic
                )
            ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    600, easing = EaseInOutCubic
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        },
        popExitTransition = {
            fadeOut(
                animationSpec = tween(
                    600, easing = EaseInOutCubic
                )
            ) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        },
        popEnterTransition = {
            fadeIn(
                animationSpec = tween(
                    600, easing = EaseInCubic
                )
            ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        }
    ) {
        composable(route = Screen.Login.name) { LoginView(navController) }
        composable(
            route = Screen.Main.name
        ) {
            MainView(
                mainViewModel,
                navController
            )

        }
        composable(route = Screen.Registration.name) {

            RegView(
                navController
            )
        }
        composable(route = Screen.Start.name) { StartView(navController) }
        composable(
            route = Screen.Basket.name,
        ) {
            BasketView(
                mainViewModel,
                navController
            )
        }
        composable(route = Screen.History.name) {
            HistoryView(
                mainViewModel,
                navController
            )
        }
    }
}

@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = true
        ).apply { targetState = true },
        modifier = Modifier,
        enter = slideInVertically(
            initialOffsetY = { 40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.6f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
    ) {
        content()
    }
}