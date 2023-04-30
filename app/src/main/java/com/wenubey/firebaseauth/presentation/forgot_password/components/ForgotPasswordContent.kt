package com.wenubey.firebaseauth.presentation.forgot_password.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wenubey.firebaseauth.core.Constants
import com.wenubey.firebaseauth.core.components.EmailTextField

@Composable
fun ForgotPasswordContent(
    paddingValues: PaddingValues,
    sendPasswordResetEmail: (email: String) -> Unit
) {
    var email by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailTextField(email = email, onEmailValueChange = { email = it })
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { sendPasswordResetEmail(email.text) }) {
            Text(text = Constants.RESET_PASSWORD, fontSize = 16.sp)
        }

    }
}