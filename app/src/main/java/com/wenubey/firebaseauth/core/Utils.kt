package com.wenubey.firebaseauth.core

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.wenubey.firebaseauth.core.Constants.CREATED_AT
import com.wenubey.firebaseauth.core.Constants.DISPLAY_NAME
import com.wenubey.firebaseauth.core.Constants.EMAIL
import com.wenubey.firebaseauth.core.Constants.PHOTO_URL
import com.wenubey.firebaseauth.core.Constants.TAG

class Utils {

    companion object {
        fun printLog(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun Context.makeToast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}

fun FirebaseUser.toUser() = mapOf(
    DISPLAY_NAME to displayName,
    EMAIL to email,
    PHOTO_URL to photoUrl?.toString(),
)