package com.wenubey.firebaseauth.presentation.sign_in.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.wenubey.firebaseauth.core.Utils.Companion.printLog
import com.wenubey.firebaseauth.core.components.ProgressBar
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.presentation.sign_in.SignInViewModel

@Composable
fun OneTapSignIn(
    viewModel: SignInViewModel = hiltViewModel(),
    launch: (result: BeginSignInResult) -> Unit
) {
    when(val result = viewModel.oneTapSignInResponse) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> result.data?.let {
            LaunchedEffect(it) {
                launch(it)
            }
        }
        is Resource.Error -> result.apply {
            LaunchedEffect(error) {
                printLog(error)
            }
        }
    }

}