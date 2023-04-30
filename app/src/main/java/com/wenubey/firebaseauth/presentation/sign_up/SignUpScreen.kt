package com.wenubey.firebaseauth.presentation.sign_up

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.Constants.VERIFY_EMAIL_MESSAGE
import com.wenubey.firebaseauth.core.Utils.Companion.makeToast
import com.wenubey.firebaseauth.presentation.sign_up.components.SendEmailVerification
import com.wenubey.firebaseauth.presentation.sign_up.components.SignUp
import com.wenubey.firebaseauth.presentation.sign_up.components.SignUpContent
import com.wenubey.firebaseauth.presentation.sign_up.components.SignUpTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            SignUpTopBar(navigateBack = navigateBack)
        },
        content = { paddingValues ->
            SignUpContent(
                paddingValues = paddingValues,
                signUp = { email, password ->
                    viewModel.signUpWithEmailAndPassword(email, password)
                },
                navigateBack = navigateBack
            )
        }
    )

    SignUp(
        sendEmailVerification = { viewModel.sendEmailVerification() },
        showVerifyEmailMessage = { context.makeToast(VERIFY_EMAIL_MESSAGE) },
    )

    SendEmailVerification()

}