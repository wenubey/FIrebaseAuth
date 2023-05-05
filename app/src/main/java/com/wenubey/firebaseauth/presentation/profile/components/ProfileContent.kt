package com.wenubey.firebaseauth.presentation.profile.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.wenubey.firebaseauth.R
import com.wenubey.firebaseauth.core.Constants.PROFILE_PICTURE_CONTENT_DESCRIPTION
import com.wenubey.firebaseauth.core.Constants.TAG

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    displayName: String?,
    photoUrl: String?,
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = 48.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context).data(photoUrl).crossfade(true).build(),
                placeholder = painterResource(id = R.drawable.baseline_person_24),
                onError = { error ->
                    Log.e(TAG, "Error ${error.result.throwable}")
                },
                contentDescription = PROFILE_PICTURE_CONTENT_DESCRIPTION,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(100.dp)
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = displayName ?: "", fontSize = 16.sp)
        }
    }
}