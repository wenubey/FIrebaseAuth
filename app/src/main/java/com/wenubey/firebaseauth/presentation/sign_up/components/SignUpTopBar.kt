package com.wenubey.firebaseauth.presentation.sign_up.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.wenubey.firebaseauth.core.Constants
import com.wenubey.firebaseauth.core.Constants.SIGN_UP_SCREEN_TITLE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopBar(
    navigateBack: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = SIGN_UP_SCREEN_TITLE)
        },

        navigationIcon = {
            IconButton(onClick = navigateBack) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = Constants.BACK_BUTTON_DESCRIPTION
                )
            }
        }
    )
}