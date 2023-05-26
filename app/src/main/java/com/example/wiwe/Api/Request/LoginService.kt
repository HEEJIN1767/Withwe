package com.example.wiwe.Api.Request

import com.example.wiwe.LoginResponse
import retrofit2.Call
import retrofit2.http.*


interface LoginService {


    //@FormUrlEncoded //레트로핏에서 @Field는  @FormUrlEncoded  와 함께 쓰인다.
    @POST("/auth/sign-in")
    @Headers("content-type: application/json", "accept: application/json")
    //서비스 url
    fun requestLgoin(//인풋을 정의

       @Body request: UserLoginRequest


    ) : Call<LoginResponse> //아웃풋을 정의하는곳
}