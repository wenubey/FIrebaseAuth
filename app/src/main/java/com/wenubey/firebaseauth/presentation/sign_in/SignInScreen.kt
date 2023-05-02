package com.wenubey.firebaseauth.presentation.sign_in

import android.app.Activity.RESULT_OK
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider.getCredential
import com.wenubey.firebaseauth.core.Constants.TAG
import com.wenubey.firebaseauth.core.Utils.Companion.makeToast
import com.wenubey.firebaseauth.core.Utils.Companion.printLog
import com.wenubey.firebaseauth.presentation.sign_in.components.OneTapSignIn
import com.wenubey.firebaseauth.presentation.sign_in.components.SignIn
import com.wenubey.firebaseauth.presentation.sign_in.components.SignInContent
import com.wenubey.firebaseauth.presentation.sign_in.components.SignInTopAppBar
import com.wenubey.firebaseauth.presentation.sign_in.components.SignInWithGoogle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToForgotPasswordScreen: () -> Unit,
    navigateToSignUpScreen: () -> Unit,
    navigateToProfileScreen:() -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            SignInTopAppBar()
        },
        content = { paddingValues ->
            SignInContent(
                paddingValues = paddingValues,
                signIn = { email, password ->
                    viewModel.signInWithEmailAndPassword(email, password)
                },
                navigateToForgotPasswordScreen = navigateToForgotPasswordScreen,
                navigateToSignUpScreen = navigateToSignUpScreen,
                oneTapSignIn = { viewModel.oneTapSignIn() }
            )
        }
    )

    SignIn(showErrorMessage = { error -> context.makeToast(error) })

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                try {
                    val credentials =
                        viewModel.oneTapClient.getSignInCredentialFromIntent(result.data)
                    val googleIdToken = credentials.googleIdToken
                    val googleCredentials = getCredential(googleIdToken, null)
                    Log.i(TAG, "User Credentials: $googleCredentials")
                    viewModel.signInWithGoogle(googleCredentials)
                } catch (it: ApiException) {
                    printLog(it)
                }
            }

        }

    fun launch(signInResult: BeginSignInResult) {
        val intent = IntentSenderRequest.Builder(signInResult.pendingIntent.intentSender).build()
        launcher.launch(intent)
    }

    OneTapSignIn(launch = { launch(it) })

    SignInWithGoogle(navigateToProfileScreen = { isSigned -> if (isSigned) { navigateToProfileScreen() } })
}