package com.wenubey.firebaseauth.presentation.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.Constants.PROFILE_SCREEN_TITLE
import com.wenubey.firebaseauth.core.Constants.PROFILE_UPDATE_FAB_DESCRIPTION
import com.wenubey.firebaseauth.core.components.TopBar
import com.wenubey.firebaseauth.presentation.profile.components.ProfileContent
import com.wenubey.firebaseauth.presentation.profile.components.RevokeAccess
import com.wenubey.firebaseauth.presentation.profile.components.UpdateUser
import com.wenubey.firebaseauth.presentation.profile.components.UserInfoUpdateDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val showDialog = remember { mutableStateOf(false) }
    var email by remember { mutableStateOf(TextFieldValue(viewModel.currentUser?.email ?: "")) }
    var displayName by remember { mutableStateOf(TextFieldValue(viewModel.currentUser?.displayName ?: "")) }
    var refresh by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopBar(
                title = PROFILE_SCREEN_TITLE,
                signOut = { viewModel.signOut() },
                revokeAccess = { viewModel.revokeAccess() })
        },
        content = { paddingValues ->
            ProfileContent(
                paddingValues = paddingValues,
                displayName = viewModel.currentUser?.displayName,
                photoUrl = viewModel.currentUser?.photoUrl.toString(),
                pickMedia = {
                })
            LaunchedEffect(refresh) { refresh = false }

        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick =
                { showDialog.value = !showDialog.value },
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = PROFILE_UPDATE_FAB_DESCRIPTION
                )
            }
        }
    )

    if (showDialog.value) {
        UserInfoUpdateDialog(
            showDialog = showDialog,
            onClickConfirm = {
                viewModel.updateUser(newDisplayName = displayName.text, email = email.text)
                showDialog.value = false
            },
            email = email,
            displayName = displayName,
            onEmailValueChange = { email = it },
            onDisplayNameValueChange = { displayName = it }
        )
    }

    RevokeAccess(
        snackbarHostState = snackbarHostState,
        coroutineScope = coroutineScope,
        signOut = { viewModel.signOut() }
    )

    UpdateUser(
        reloadNeed = { reloadNeed ->
            refresh = reloadNeed
        },
        snackbarHostState = snackbarHostState
    )

}