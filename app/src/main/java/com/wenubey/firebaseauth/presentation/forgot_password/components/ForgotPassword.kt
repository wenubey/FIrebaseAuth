package com.wenubey.firebaseauth.presentation.forgot_password.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.components.ProgressBar
import com.wenubey.firebaseauth.core.Utils.Companion.printLog
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.presentation.forgot_password.ForgotPasswordViewModel

@Composable
fun ForgotPassword(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
    showResetPasswordMessage: () -> Unit,
    showErrorMessage: (error: String?) -> Unit
) {
    when(val response = viewModel.isSentPasswordReset) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> {
            val isResetEmailSent = response.data
            LaunchedEffect(isResetEmailSent) {
                if (isResetEmailSent) {
                    navigateBack()
                    showResetPasswordMessage()
                }
            }
        }
        is Resource.Error -> response.apply {
            LaunchedEffect(error) {
                printLog(error)
                showErrorMessage(error.message)
            }
        }
    }
}