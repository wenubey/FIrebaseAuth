package com.wenubey.firebaseauth.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.domain.repository.ProfileRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
   private val auth: FirebaseAuth,
): ProfileRepository  {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override fun signOut(): Resource<Boolean> {
        return try {
            auth.signOut()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun revokeAccess(): Resource<Boolean> {
        return try {
            auth.currentUser?.delete()?.await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun reloadUser(): Resource<Boolean> {
        return try {
            auth.currentUser?.reload()?.await()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}