package com.wenubey.firebaseauth.presentation.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.domain.repository.AuthEmailPasswordRepository
import com.wenubey.firebaseauth.domain.repository.AuthGoogleSignInRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val emailPasswordRepo: AuthEmailPasswordRepository,
    private val googleSignRepo: AuthGoogleSignInRepository,
    val oneTapClient: SignInClient
    ): ViewModel() {

    var isUserSigned by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set

    val isUserAuthenticated get() = googleSignRepo.isUserAuthenticatedInFirebase

    var oneTapSignInResponse by mutableStateOf<Resource<BeginSignInResult>>(Resource.Success(null))
        private set

    var signInWithGoogleResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set

    fun oneTapSignIn() = viewModelScope.launch {
        oneTapSignInResponse = Resource.Loading

        oneTapSignInResponse = googleSignRepo.oneTapSignInWithGoogle()
    }

    fun signInWithGoogle(googleCredential: AuthCredential) = viewModelScope.launch {
        signInWithGoogleResponse = Resource.Loading

        signInWithGoogleResponse = googleSignRepo.firebaseSignInWithGoogle(googleCredential)
    }

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        isUserSigned = Resource.Loading

        isUserSigned = emailPasswordRepo.signInWithEmailAndPassword(email, password)
    }
}