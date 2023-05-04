package com.wenubey.firebaseauth.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wenubey.firebaseauth.core.Constants
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.domain.model.toUser
import com.wenubey.firebaseauth.domain.repository.AuthGoogleSignInRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthGoogleSignInRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named(Constants.SIGN_IN_REQUEST)
    private var signInRequest: BeginSignInRequest,
    @Named(Constants.SIGN_UP_REQUEST)
    private var signUpRequest: BeginSignInRequest,
    private val db: FirebaseFirestore,
) : AuthGoogleSignInRepository {
    override val isUserAuthenticatedInFirebase = auth.currentUser != null

    override suspend fun oneTapSignInWithGoogle(): Resource<BeginSignInResult> {
        return try {
            val signInResult = oneTapClient.beginSignIn(signInRequest).await()
            Resource.Success(signInResult)
        } catch (e: Exception) {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                Resource.Success(signUpResult)
            } catch (e: Exception) {
                Resource.Error(e)
            }
        }
    }

    override suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Resource<Boolean> {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFirestore()
            }
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    private fun addUserToFirestore() {
        auth.currentUser?.apply {
            val user = toUser()
            db.collection(Constants.USERS).document(uid).set(user)
        }
    }
}