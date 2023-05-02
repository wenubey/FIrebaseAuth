package com.wenubey.firebaseauth.presentation.profile.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.Constants.ACCESS_REVOKED_MESSAGE
import com.wenubey.firebaseauth.core.Constants.REVOKE_ACCESS_MESSAGE
import com.wenubey.firebaseauth.core.Constants.SENSITIVE_OPERATION_MESSAGE
import com.wenubey.firebaseauth.core.Constants.SIGN_OUT
import com.wenubey.firebaseauth.core.components.ProgressBar
import com.wenubey.firebaseauth.core.Utils.Companion.makeToast
import com.wenubey.firebaseauth.core.Utils.Companion.printLog
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.presentation.profile.ProfileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RevokeAccess(
    viewModel: ProfileViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    signOut: () -> Unit,
) {
    val context = LocalContext.current

    fun showRevokeAccessMessage() = coroutineScope.launch {
        val result = snackbarHostState.showSnackbar(
            message = REVOKE_ACCESS_MESSAGE,
            actionLabel = SIGN_OUT
        )
        if (result == SnackbarResult.ActionPerformed) {
            viewModel.signOut()
        }
    }

    when (val result = viewModel.revokeAccessResponse) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> {
            val isAccessRevoked = result.data
            LaunchedEffect(isAccessRevoked) {
                if (isAccessRevoked!!) {
                    context.makeToast(ACCESS_REVOKED_MESSAGE)
                }
            }
        }

        is Resource.Error -> result.apply {
            LaunchedEffect(error) {
                printLog(error)
                if (error.message == SENSITIVE_OPERATION_MESSAGE) {
                    showRevokeAccessMessage()
                }
            }
        }
    }
}