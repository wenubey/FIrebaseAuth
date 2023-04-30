package com.wenubey.firebaseauth.presentation.sign_in

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.Utils.Companion.makeToast
import com.wenubey.firebaseauth.presentation.sign_in.components.SignIn
import com.wenubey.firebaseauth.presentation.sign_in.components.SignInContent
import com.wenubey.firebaseauth.presentation.sign_in.components.SignInTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            SignInTopAppBar()
        },
        content = { paddingValues ->
            SignInContent(
                paddingValues = paddingValues,
                signIn = { email, password ->
                    viewModel.signInWithEmailAndPassword(email, password)
                },
                navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
                navigateToSignUpScreen = navigateToSignUpScreen
            )
        }
    )

    SignIn(showErrorMessage = { error -> context.makeToast(error) })
}