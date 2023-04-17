package com.example.wiwe

import retrofit2.Call
import retrofit2.http.*

interface RegisterService {


    @FormUrlEncoded
    @POST("/auth/signup")
    @Headers("content-type: application/json", "accept: application/json")
    fun register(
        @Body request: UserRegisterRequest
        //  @Field("name") name: String,
      //  @Field("nickname") nickname: String,
        // @Field("password") password: String,
       // @Field("username") username: String

    ): Call<RigisterResponse>
}