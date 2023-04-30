package com.wenubey.firebaseauth.presentation.sign_in.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.wenubey.firebaseauth.core.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInTopAppBar() {
    TopAppBar(
        title = {
            Text(text = Constants.SIGN_IN_SCREEN_TITLE)
        }
    )
}