package com.wenubey.firebaseauth.navigation

sealed class Screen(val route: String) {

    object SignInScreen: Screen("signInScreen")

    object SignUpScreen: Screen("signUpScreen")

    object ProfileScreen: Screen("profileScreen")

    object ForgotPasswordScreen: Screen("forgotPasswordScreen")

    object VerifyEmailScreen: Screen("verifyEmailScreen")
}
