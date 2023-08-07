package com.yangyinxu.finitude.data.remote

import com.yangyinxu.finitude.data.remote.archResponses.Posts
import com.yangyinxu.finitude.data.remote.archResponses.login.LoginRequestBody
import com.yangyinxu.finitude.data.remote.archResponses.login.LoginResponse
import com.yangyinxu.finitude.data.remote.archResponses.signUp.SignUpRequestBody
import com.yangyinxu.finitude.data.remote.archResponses.signUp.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ArchtreeApi {

    @GET("feed/posts")
    suspend fun getPosts(): Posts

    @PUT("auth/signup")
    suspend fun putSignUp(@Body signUpRequestBody: SignUpRequestBody): SignUpResponse

    @POST("auth/login")
    suspend fun postLogin(@Body loginRequestBody: LoginRequestBody): LoginResponse
}