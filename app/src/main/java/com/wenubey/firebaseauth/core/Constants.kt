package com.wenubey.firebaseauth.core

object Constants {
    //TAG for Log
    const val TAG = "authApp"

    // SCREENS
    const val SIGN_IN_SCREEN = "signInScreen"
    const val FORGOT_PASSWORD_SCREEN = "forgotPasswordScreen"
    const val SIGN_UP_SCREEN = "signUpScreen"
    const val VERIFY_EMAIL_SCREEN = "verifyEmailScreen"
    const val PROFILE_SCREEN = "profileScreen"

    //SCREEN TITLES
    const val FORGOT_PASSWORD_SCREEN_TITLE = "Forgot Password"
    const val SIGN_IN_SCREEN_TITLE = "Sign In"
    const val PROFILE_SCREEN_TITLE = "Profile"
    const val SIGN_UP_SCREEN_TITLE = "Sign Up"
    const val VERIFY_EMAIL_SCREEN_TITLE = "Verify Screen"


    // BUTTONS
    const val SIGN_IN = "Sign in"
    const val RESET_PASSWORD = "Reset"
    const val SIGN_UP = "Sign up"
    const val SIGN_OUT = "Sign out"
    const val REVOKE_ACCESS = "Revoke Access"


    //TEXTS
    const val FORGOT_PASSWORD = "Forgot password?"
    const val NO_ACCOUNT = "Don't you have an account? Sign Up!"
    const val REVOKE_ACCESS_MESSAGE = "You need to re-authenticate before revoking the access."
    const val ALREADY_USER = "Already a user? Sign in."
    const val ALREADY_VERIFIED = "Already verified?"
    const val SPAM_EMAIL = "If not, please also check the spam folder."



    //CONTENT DESCRIPTIONS
    const val BACK_BUTTON_DESCRIPTION = "Back to previous screen"
    const val PASSWORD_VISIBILITY_DESCRIPTION = "Password visibility on/off"
    const val OPEN_MENU_DESCRIPTION = "This buttons opens the menu"


    //LABELS
    const val EMAIL_LABEL = "Email"
    const val PASSWORD_LABEL = "Password"

    //MESSAGES
    const val RESET_PASSWORD_MESSAGE = "We've sent you an email with a link to reset the password."
    const val ACCESS_REVOKED_MESSAGE = "Your access has been revoked."
    const val EMAIL_NOT_VERIFIED_MESSAGE = "Your email is not verified."
    const val SENSITIVE_OPERATION_MESSAGE = "This operation is sensitive and requires recent authentication. Log in again before retrying this request."
    const val VERIFY_EMAIL_MESSAGE = "We've sent you an email with a link to verify the email."


}