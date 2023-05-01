package com.wenubey.firebaseauth.presentation.verify_email

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.Constants.EMAIL_NOT_VERIFIED_MESSAGE
import com.wenubey.firebaseauth.core.Constants.VERIFY_EMAIL_SCREEN_TITLE
import com.wenubey.firebaseauth.core.Utils.Companion.makeToast
import com.wenubey.firebaseauth.core.components.TopBar
import com.wenubey.firebaseauth.presentation.profile.ProfileViewModel
import com.wenubey.firebaseauth.presentation.profile.components.RevokeAccess
import com.wenubey.firebaseauth.presentation.verify_email.components.ReloadUser
import com.wenubey.firebaseauth.presentation.verify_email.components.VerifyEmailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyEmailScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToProfileScreen: () -> Unit,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        topBar = {
            TopBar(
                title = VERIFY_EMAIL_SCREEN_TITLE,
                signOut = { viewModel.signOut() },
                revokeAccess = { viewModel.revokeAccess() })
        },
        content = { paddingValues ->
            VerifyEmailContent(
                paddingValues = paddingValues,
                reloadUser = { viewModel.reloadUser() })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )

    ReloadUser(navigateToProfileScreen = {
        if (viewModel.isEmailVerified) {
            navigateToProfileScreen()
        } else {
            context.makeToast(EMAIL_NOT_VERIFIED_MESSAGE)
        }
    })

    RevokeAccess(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        signOut = { viewModel.signOut() })
}