package com.wenubey.firebaseauth.presentation.profile.components

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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseUser
import com.wenubey.firebaseauth.R
import com.wenubey.firebaseauth.core.Constants.PROFILE_PICTURE_CONTENT_DESCRIPTION

@Composable
fun ProfileContent(
    paddingValues: PaddingValues,
    user: FirebaseUser?
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

            val photo = if (user?.photoUrl != null) user.photoUrl else R.drawable.baseline_person_24
            AsyncImage(
                model = ImageRequest.Builder(context).data(photo).crossfade(true).build(),
                contentDescription = PROFILE_PICTURE_CONTENT_DESCRIPTION,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(96.dp)
                    .height(96.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = user?.displayName ?: "Null", fontSize = 16.sp)
        }
    }
}