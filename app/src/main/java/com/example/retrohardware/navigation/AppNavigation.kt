package com.example.retrohardware.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.retrohardware.data.AuthRepository
import com.example.retrohardware.data.HardwareItem
import com.example.retrohardware.screens.DetailScreen
import com.example.retrohardware.screens.HomeScreen
import com.example.retrohardware.screens.LoginScreen
import com.example.retrohardware.screens.RegisterScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val authRepository = remember {
        AuthRepository()
    }

    var selectedItem by remember {
        mutableStateOf<HardwareItem?>(null)
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // LOGIN
        composable("login") {

            LoginScreen(

                onLoginSuccess = {

                    navController.navigate("home") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                },

                onRegisterClick = {

                    navController.navigate("register")
                }
            )
        }

        // CADASTRO
        composable("register") {

            RegisterScreen(

                onRegisterSuccess = {

                    navController.navigate("home") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                },

                onBackClick = {

                    navController.popBackStack()
                }
            )
        }

        // HOME
        composable("home") {

            HomeScreen(

                onItemClick = { item ->

                    selectedItem = item

                    navController.navigate("detail")
                },

                onLogout = {

                    authRepository.logout()

                    navController.navigate("login") {

                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // DETALHES
        composable("detail") {

            selectedItem?.let { item ->

                DetailScreen(

                    item = item,

                    onBackClick = {

                        navController.popBackStack()
                    }
                )
            }
        }
    }
}