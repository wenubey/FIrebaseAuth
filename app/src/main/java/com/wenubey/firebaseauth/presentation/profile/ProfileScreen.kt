package com.wenubey.firebaseauth.presentation.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.Constants.PROFILE_SCREEN_TITLE
import com.wenubey.firebaseauth.core.components.TopBar
import com.wenubey.firebaseauth.presentation.profile.components.ProfileContent
import com.wenubey.firebaseauth.presentation.profile.components.RevokeAccess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopBar(
                title = PROFILE_SCREEN_TITLE,
                signOut = { viewModel.signOut() },
                revokeAccess = { viewModel.revokeAccess() })
        },
        content = { paddingValues ->
            ProfileContent(paddingValues = paddingValues)

        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )

    RevokeAccess(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        signOut = { viewModel.signOut() }
    )
}