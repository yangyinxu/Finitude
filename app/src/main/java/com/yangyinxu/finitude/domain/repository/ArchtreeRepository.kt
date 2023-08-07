package com.yangyinxu.finitude.domain.repository

import com.yangyinxu.finitude.data.remote.ArchtreeApi
import com.yangyinxu.finitude.data.remote.archResponses.Posts
import com.yangyinxu.finitude.data.remote.archResponses.login.LoginRequestBody
import com.yangyinxu.finitude.data.remote.archResponses.login.LoginResponse
import com.yangyinxu.finitude.data.remote.archResponses.signUp.SignUpRequestBody
import com.yangyinxu.finitude.data.remote.archResponses.signUp.SignUpResponse
import com.yangyinxu.finitude.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ArchtreeRepository @Inject constructor(
    private val api: ArchtreeApi
) {

    suspend fun getPosts(): Resource<Posts> {
        val response = try {
            api.getPosts()
        } catch (e: Exception) {
            return Resource.Error("An unknown error has occurred.")
        }
        return Resource.Success(response);
    }

    suspend fun putSignUp(signUpRequestBody: SignUpRequestBody): Resource<SignUpResponse> {
        val response = try {
            api.putSignUp(signUpRequestBody)
        } catch (e: Exception) {
            return Resource.Error("An unknown error has occurred.")
        }
        return Resource.Success(response);
    }

    suspend fun postLogin(loginRequestBody: LoginRequestBody): Resource<LoginResponse> {
        val response = try {
            api.postLogin(loginRequestBody)
        } catch (e: Exception) {
            return Resource.Error("An unknown error has occurred.")
        }
        return Resource.Success(response);
    }
}