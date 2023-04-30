package com.wenubey.firebaseauth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wenubey.firebaseauth.navigation.Screen.*
import com.wenubey.firebaseauth.presentation.forgot_password.ForgotPasswordScreen
import com.wenubey.firebaseauth.presentation.sign_in.SignInScreen
import com.wenubey.firebaseauth.presentation.sign_up.SignUpScreen
import com.wenubey.firebaseauth.presentation.profile.ProfileScreen
import com.wenubey.firebaseauth.presentation.verify_email.VerifyEmailScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = SignInScreen.route) {
        composable(route = SignInScreen.route) {
            SignInScreen()
        }
        composable(route = SignUpScreen.route) {
            SignUpScreen()
        }
        composable(route = ForgotPasswordScreen.route) {
            ForgotPasswordScreen()
        }
        composable(route = ProfileScreen.route) {
            ProfileScreen()
        }
        composable(route = VerifyEmailScreen.route) {
            VerifyEmailScreen()
        }
    }
}