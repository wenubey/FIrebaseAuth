package com.wenubey.firebaseauth.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wenubey.firebaseauth.navigation.Screen

@Composable
fun AuthState(
    viewModel: MainViewModel = hiltViewModel(),
    navController: NavController
) {
    val isUserSignedOut = viewModel.getAuthState().collectAsState().value
    if (isUserSignedOut) {
        NavigateToSignInScreen(navController)
    } else {
        if (viewModel.isEmailVerified) {
            NavigateToProfileScreen(navController)
        } else {
            NavigateToVerifyEmailScreen(navController)
        }
    }
}


@Composable
private fun NavigateToSignInScreen(navController: NavController) =
    navController.navigate(Screen.SignInScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

@Composable
private fun NavigateToProfileScreen(navController: NavController) =
    navController.navigate(Screen.ProfileScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

@Composable
private fun NavigateToVerifyEmailScreen(navController: NavController) = navController.navigate(
    Screen.VerifyEmailScreen.route
) {
    popUpTo(navController.graph.id) {
        inclusive = true
    }
}