package com.wenubey.firebaseauth.presentation.sign_in.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.components.ProgressBar
import com.wenubey.firebaseauth.core.components.Utils.Companion.printLog
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.presentation.sign_in.SignInViewModel

@Composable
fun SignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    showErrorMessage: (error: String?) -> Unit
) {
    when (val result = viewModel.isUserSigned) {
        is Resource.Success -> Unit
        is Resource.Loading ->  ProgressBar()
        is Resource.Error -> result.apply {
            LaunchedEffect(error) {
                printLog(error)
                showErrorMessage(error.message)
            }
        }

    }
}