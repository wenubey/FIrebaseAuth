package com.wenubey.firebaseauth.presentation.profile.components

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.firebaseauth.core.Constants.USER_SUCCESSFULLY_UPDATE_MESSAGE
import com.wenubey.firebaseauth.core.Constants.USER_UPDATE_ERROR_MESSAGE
import com.wenubey.firebaseauth.core.Utils.Companion.printLog
import com.wenubey.firebaseauth.core.components.ProgressBar
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.presentation.profile.ProfileViewModel

@Composable
fun UpdateUser(
    viewModel: ProfileViewModel = hiltViewModel(),
    reloadNeed: (Boolean) -> Unit,
    snackbarHostState: SnackbarHostState
) {
    when (val result = viewModel.updateUserResponse) {
        is Resource.Loading -> ProgressBar()
        is Resource.Success -> {
            val isUserUpdated = result.data
            LaunchedEffect(isUserUpdated) {
                if (isUserUpdated!!) {
                    reloadNeed(true)
                    snackbarHostState.showSnackbar(USER_SUCCESSFULLY_UPDATE_MESSAGE)
                } else {
                    reloadNeed(false)

                }
            }
        }

        is Resource.Error -> result.apply {
            LaunchedEffect(error) {
                printLog(error)
                snackbarHostState.showSnackbar(USER_UPDATE_ERROR_MESSAGE + error.localizedMessage)
            }
        }
    }
}