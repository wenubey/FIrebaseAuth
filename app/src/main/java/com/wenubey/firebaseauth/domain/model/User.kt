package com.wenubey.firebaseauth.domain.model

import com.google.firebase.auth.FirebaseUser

data class User(
    val displayName: String? = null,
    val email: String? = null,
    val photoUrl: String? = null,
    val phoneNumber: String? = null,
)

fun FirebaseUser.toUser(): User {
    return User(
        displayName = displayName,
        email = email,
        photoUrl = photoUrl.toString(),
        phoneNumber = phoneNumber
    )
}
