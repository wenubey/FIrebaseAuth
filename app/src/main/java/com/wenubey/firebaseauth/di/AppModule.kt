package com.wenubey.firebaseauth.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wenubey.firebaseauth.data.repository.AuthRepositoryImpl
import com.wenubey.firebaseauth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth
    )
}