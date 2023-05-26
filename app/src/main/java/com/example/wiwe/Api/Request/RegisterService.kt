package com.example.wiwe.Api.Request

import com.example.wiwe.RigisterResponse
import retrofit2.Call
import retrofit2.http.*

interface RegisterService {


    //@FormUrlEncoded
    @POST("/auth/signup")
    @Headers("content-type: application/json", "accept: application/json")
    fun register(
        @Body request: UserRegisterRequest
    ): Call<RigisterResponse>
}