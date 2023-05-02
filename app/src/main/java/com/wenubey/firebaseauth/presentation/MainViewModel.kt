package com.wenubey.firebaseauth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.firebaseauth.domain.repository.AuthEmailPasswordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: AuthEmailPasswordRepository
): ViewModel() {

    init {
        getAuthState()
    }

    fun getAuthState() = repo.getAuthState(viewModelScope)

    val isEmailVerified get() = repo.currentUser?.isEmailVerified ?: false
}