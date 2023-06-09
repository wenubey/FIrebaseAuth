package com.wenubey.firebaseauth.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.wenubey.firebaseauth.domain.model.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface AuthEmailPasswordRepository {

    val currentUser: FirebaseUser?

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Resource<Boolean>

    suspend fun sendEmailVerification(): Resource<Boolean>

    suspend fun signInWithEmailAndPassword(email: String, password: String): Resource<Boolean>

    suspend fun sendPasswordResetEmail(email: String): Resource<Boolean>

    fun getAuthState(viewModelScope: CoroutineScope): StateFlow<Boolean>

}