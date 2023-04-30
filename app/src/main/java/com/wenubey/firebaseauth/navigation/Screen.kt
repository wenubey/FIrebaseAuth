package com.wenubey.firebaseauth.navigation

import com.wenubey.firebaseauth.core.Constants.FORGOT_PASSWORD_SCREEN
import com.wenubey.firebaseauth.core.Constants.PROFILE_SCREEN
import com.wenubey.firebaseauth.core.Constants.SIGN_IN_SCREEN
import com.wenubey.firebaseauth.core.Constants.SIGN_UP_SCREEN
import com.wenubey.firebaseauth.core.Constants.VERIFY_EMAIL_SCREEN

sealed class Screen(val route: String) {

    object SignInScreen: Screen(SIGN_IN_SCREEN)

    object SignUpScreen: Screen(SIGN_UP_SCREEN)

    object ProfileScreen: Screen(PROFILE_SCREEN)

    object ForgotPasswordScreen: Screen(FORGOT_PASSWORD_SCREEN)

    object VerifyEmailScreen: Screen(VERIFY_EMAIL_SCREEN)
}
