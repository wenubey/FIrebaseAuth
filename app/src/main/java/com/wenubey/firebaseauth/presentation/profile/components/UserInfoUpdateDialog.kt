package com.wenubey.firebaseauth.presentation.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.wenubey.firebaseauth.core.Constants
import com.wenubey.firebaseauth.core.components.EmailTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoUpdateDialog(
    showDialog: MutableState<Boolean>,
    email: TextFieldValue,
    onEmailValueChange: (email: TextFieldValue) -> Unit,
    displayName: TextFieldValue,
    onDisplayNameValueChange: (displayName: TextFieldValue) -> Unit,
    onClickConfirm: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        title = { Text(text = Constants.PROFILE_INFO) },
        text = {
            Column(
                modifier = Modifier.padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailTextField(email = email,
                    onEmailValueChange = onEmailValueChange,
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ))
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = displayName,
                    onValueChange = onDisplayNameValueChange,
                    label = {
                        Text(
                            text = Constants.DISPLAY_NAME_LABEL,
                        )
                    },
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onClickConfirm) {
                Text(text = Constants.SAVE)
            }
        })
}