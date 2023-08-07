package com.yangyinxu.finitude.di

import com.yangyinxu.finitude.domain.use_case.ValidateEmail
import com.yangyinxu.finitude.domain.use_case.ValidatePassword
import com.yangyinxu.finitude.domain.use_case.ValidateRepeatedPassword
import com.yangyinxu.finitude.domain.use_case.ValidateTerms
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    fun provideValidateEmail() : ValidateEmail = ValidateEmail();

    @Provides
    fun provideValidatePassword() : ValidatePassword = ValidatePassword();

    @Provides
    fun provideValidateRepeatedPassword() : ValidateRepeatedPassword = ValidateRepeatedPassword();

    @Provides
    fun provideValidateTerms() : ValidateTerms = ValidateTerms();
}