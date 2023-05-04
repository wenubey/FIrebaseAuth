package com.wenubey.firebaseauth.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: ProfileRepository
) : ViewModel() {

    var revokeAccessResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))

    var reloadUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))

    var updateUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))

    val currentUser get() = repo.currentUser

    fun reloadUser() = viewModelScope.launch {
        reloadUserResponse = Resource.Loading

        reloadUserResponse = repo.reloadUser()
    }

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false

    fun signOut() = repo.signOut()

    fun revokeAccess() = viewModelScope.launch {
        revokeAccessResponse = Resource.Loading

        revokeAccessResponse = repo.revokeAccess()
    }

    fun updateUser(newDisplayName: String, email: String) = viewModelScope.launch {
        updateUserResponse = Resource.Loading

        updateUserResponse = repo.updateUser(newDisplayName, email)
    }

    fun updateUserProfilePhoto(photoUrl:String) = viewModelScope.launch {
        repo.updateUserProfilePhoto(photoUrl)
    }

}