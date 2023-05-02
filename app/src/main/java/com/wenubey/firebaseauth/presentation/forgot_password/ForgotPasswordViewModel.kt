package com.wenubey.firebaseauth.presentation.forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.firebaseauth.domain.model.Resource
import com.wenubey.firebaseauth.domain.repository.AuthEmailPasswordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val repo: AuthEmailPasswordRepository
): ViewModel() {

    var isSentPasswordReset by mutableStateOf<Resource<Boolean>>(Resource.Success(false))

    fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        isSentPasswordReset = Resource.Loading

        isSentPasswordReset = repo.sendPasswordResetEmail(email)
    }

}