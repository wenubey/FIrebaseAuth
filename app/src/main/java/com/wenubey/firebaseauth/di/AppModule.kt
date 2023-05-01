package com.wenubey.firebaseauth.di

import android.app.Application
import android.content.Context
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.wenubey.firebaseauth.core.Constants.SIGN_IN_REQUEST
import com.wenubey.firebaseauth.core.Constants.SIGN_UP_REQUEST
import com.wenubey.firebaseauth.core.Constants.WEB_CLIENT_ID
import com.wenubey.firebaseauth.data.repository.AuthRepositoryImpl
import com.wenubey.firebaseauth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        @Named(SIGN_IN_REQUEST) signInRequest: BeginSignInRequest,
        @Named(
            SIGN_UP_REQUEST
        ) signUpRequest: BeginSignInRequest,
        db: FirebaseFirestore
    ): AuthRepository =
        AuthRepositoryImpl(
            auth = auth,
            oneTapClient = oneTapClient,
            signInRequest = signInRequest,
            signUpRequest = signUpRequest,
            db = db
        )

    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideOneTapClient(
        @ApplicationContext context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQUEST)
    fun provideSignInRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(WEB_CLIENT_ID)
                .setFilterByAuthorizedAccounts(true)
                .build()
        ).setAutoSelectEnabled(true)
        .build()

    @Provides
    @Named(SIGN_UP_REQUEST)
    fun provideSignUpRequest(app: Application) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(WEB_CLIENT_ID)
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    @Provides
    fun provideGoogleSignInOptions(app: Application) =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(WEB_CLIENT_ID)
            .requestEmail()
            .build()

    @Provides
    fun provideGoogleSignInClient(app: Application, options: GoogleSignInOptions) =
        GoogleSignIn.getClient(app, options)


}