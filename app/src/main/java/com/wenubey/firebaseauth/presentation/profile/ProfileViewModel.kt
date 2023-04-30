package com.wenubey.firebaseauth.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    var revokeAccessResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))

    var reloadUserResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))

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

}