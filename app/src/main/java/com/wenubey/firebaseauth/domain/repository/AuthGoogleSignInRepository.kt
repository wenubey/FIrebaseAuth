package com.wenubey.firebaseauth.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import com.wenubey.firebaseauth.domain.model.Resource

interface AuthGoogleSignInRepository {

    val isUserAuthenticatedInFirebase: Boolean

    suspend fun oneTapSignInWithGoogle(): Resource<BeginSignInResult>

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential) : Resource<Boolean>
}