package com.wenubey.firebaseauth.presentation.sign_up

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
class SignUpViewModel @Inject constructor(
    private val repo: AuthEmailPasswordRepository
) : ViewModel() {

    var signUpResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set

    var sendEmailVerificationResponse by mutableStateOf<Resource<Boolean>>(Resource.Success(false))
        private set

    fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signUpResponse = Resource.Loading

        signUpResponse = repo.signUpWithEmailAndPassword(email, password)
    }

    fun sendEmailVerification() = viewModelScope.launch {
        sendEmailVerificationResponse = Resource.Loading

        sendEmailVerificationResponse = repo.sendEmailVerification()
    }
}