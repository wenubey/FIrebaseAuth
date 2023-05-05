package com.wenubey.firebaseauth.core

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.wenubey.firebaseauth.core.Constants.TAG

class Utils {

    companion object {
        fun printLog(e: Exception) = Log.e(TAG, e.stackTraceToString())

        fun Context.makeToast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()


    }
}
