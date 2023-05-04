package com.wenubey.firebaseauth.di

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wenubey.firebaseauth.core.Constants.SIGN_IN_REQUEST
import com.wenubey.firebaseauth.core.Constants.SIGN_UP_REQUEST
import com.wenubey.firebaseauth.data.repository.AuthEmailPasswordRepositoryImpl
import com.wenubey.firebaseauth.data.repository.AuthGoogleSignInRepositoryImpl
import com.wenubey.firebaseauth.data.repository.ProfileRepositoryImpl
import com.wenubey.firebaseauth.domain.repository.AuthEmailPasswordRepository
import com.wenubey.firebaseauth.domain.repository.AuthGoogleSignInRepository
import com.wenubey.firebaseauth.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthEmailPasswordRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): AuthEmailPasswordRepository =
        AuthEmailPasswordRepositoryImpl(
            auth = auth,
            db = db
        )

    @Provides
    fun provideAuthGoogleSignInRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore,
        oneTapClient: SignInClient,
        @Named(SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest
    ): AuthGoogleSignInRepository =
        AuthGoogleSignInRepositoryImpl(
            auth = auth,
            db = db,
            oneTapClient = oneTapClient,
            signInRequest = signInRequest,
            signUpRequest = signUpRequest
        )


    @Provides
    fun provideProfileRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): ProfileRepository =
        ProfileRepositoryImpl(auth = auth, db = db)

}