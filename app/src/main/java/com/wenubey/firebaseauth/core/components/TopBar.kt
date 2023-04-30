package com.wenubey.firebaseauth.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wenubey.firebaseauth.core.Constants.OPEN_MENU_DESCRIPTION
import com.wenubey.firebaseauth.core.Constants.REVOKE_ACCESS
import com.wenubey.firebaseauth.core.Constants.SIGN_OUT

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    signOut: () -> Unit,
    revokeAccess: () -> Unit
) {
    var openMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = title)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    IconButton(onClick = { openMenu = !openMenu }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = OPEN_MENU_DESCRIPTION
                        )
                    }
                }
            }
        },
        actions = {
            DropdownMenu(expanded = openMenu, onDismissRequest = { openMenu = !openMenu }) {
                DropdownMenuItem(
                    text = { Text(text = SIGN_OUT) },
                    onClick = {
                        signOut()
                        openMenu = !openMenu
                    },
                )
                DropdownMenuItem(
                    text = { Text(text = REVOKE_ACCESS) },
                    onClick = {
                        revokeAccess()
                        openMenu = !openMenu
                    },
                )
            }
        }
    )
}