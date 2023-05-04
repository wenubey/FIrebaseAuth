package com.wenubey.firebaseauth.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.wenubey.firebaseauth.domain.model.Resource

interface ProfileRepository {
    val currentUser: FirebaseUser?

    fun signOut(): Resource<Boolean>

    suspend fun revokeAccess(): Resource<Boolean>

    suspend fun reloadUser(): Resource<Boolean>

    suspend fun updateUser(newDisplayName: String, email: String) : Resource<Boolean>

    suspend fun updateUserProfilePhoto(newPhotoUrl: String): Resource<Boolean>

}