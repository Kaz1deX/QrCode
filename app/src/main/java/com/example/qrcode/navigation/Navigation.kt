package com.example.qrcode.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qrcode.ui.screen.QrCodeGenerationScreen
import com.example.qrcode.ui.screen.QrCodeScanScreen

object NavigationRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.QrCodeGenerationScreen)
}

@Composable
fun Navigation(
    navController: NavHostController,
    activityContext: Context
) {
    NavHost(navController = navController, startDestination = Screen.QrCodeGenerationScreen.route) {
        composable(route = Screen.QrCodeGenerationScreen.route) {
            NavigationRouter.currentScreen.value = Screen.QrCodeGenerationScreen

            QrCodeGenerationScreen(
                onNextButtonPressed = { navController.navigate(Screen.QrCodeScanScreen.route) }
            )
        }
        composable(route = Screen.QrCodeScanScreen.route) {
            NavigationRouter.currentScreen.value = Screen.QrCodeScanScreen

            QrCodeScanScreen(
                onBackButtonPressed = { navController.popBackStack() }
            )
        }
    }
}