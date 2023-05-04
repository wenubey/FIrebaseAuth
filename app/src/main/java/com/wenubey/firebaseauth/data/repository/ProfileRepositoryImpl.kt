package com.wenubey.firebaseauth.data.repository

import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.wenubey.firebaseauth.core.Constants
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.domain.repository.ProfileRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
   private val auth: FirebaseAuth,
   private val db: FirebaseFirestore
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

    override suspend fun updateUser(newDisplayName: String, email: String): Resource<Boolean> {
        return try {
            val profileUpdates = userProfileChangeRequest {
                displayName = newDisplayName
            }
            auth.currentUser?.updateProfile(profileUpdates)?.await()
            auth.currentUser?.updateEmail(email)?.await()
            val updatedValue = mapOf(
                "displayName" to newDisplayName,
                "email" to email
            )
            db.collection(Constants.USERS).document(auth.currentUser!!.uid).update(updatedValue)
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun updateUserProfilePhoto(newPhotoUrl: String): Resource<Boolean> {
        return try {
            val profileUpdates = userProfileChangeRequest {
                photoUri = newPhotoUrl.toUri()
            }
            auth.currentUser?.updateProfile(profileUpdates)?.await()
            db.collection(Constants.USERS).document(auth.currentUser!!.uid).update("photoUrl", newPhotoUrl)
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}