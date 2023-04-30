package com.wenubey.firebaseauth.presentation.forgot_password

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.Constants.RESET_PASSWORD_MESSAGE
import com.wenubey.firebaseauth.core.Utils.Companion.makeToast
import com.wenubey.firebaseauth.presentation.forgot_password.components.ForgotPassword
import com.wenubey.firebaseauth.presentation.forgot_password.components.ForgotPasswordContent
import com.wenubey.firebaseauth.presentation.forgot_password.components.ForgotPasswordTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            ForgotPasswordTopAppBar(navigateBack = navigateBack)
        },
        content = { paddingValues ->
            ForgotPasswordContent(
                paddingValues = paddingValues,
                sendPasswordResetEmail = { email ->
                    viewModel.sendPasswordResetEmail(email)
                }
            )
        }
    )

    ForgotPassword(
        navigateBack = navigateBack,
        showResetPasswordMessage = { context.makeToast(RESET_PASSWORD_MESSAGE) },
        showErrorMessage = { error -> context.makeToast(error) }
    )
}