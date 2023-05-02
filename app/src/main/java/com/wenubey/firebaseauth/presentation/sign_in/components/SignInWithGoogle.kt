package com.wenubey.firebaseauth.presentation.sign_in.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.Utils.Companion.printLog
import com.wenubey.firebaseauth.core.components.ProgressBar
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.presentation.sign_in.SignInViewModel

@Composable
fun SignInWithGoogle(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToProfileScreen: (isSigned: Boolean) -> Unit
) {
    when(val result = viewModel.signInWithGoogleResponse) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> result.data?.let{ isSigned ->
            LaunchedEffect(isSigned) {
                navigateToProfileScreen(isSigned)
            }
        }
        is Resource.Error -> result.apply {
            LaunchedEffect(error) {
                printLog(error)
            }
        }
    }
}