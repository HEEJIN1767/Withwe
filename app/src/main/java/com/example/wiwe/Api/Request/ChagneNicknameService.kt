package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.ReadCommunityResponse
import com.example.wiwe.Api.Response.UserInfoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ChagneNicknameService{
    //닉네임 변경
    @PUT("/user/changeNickname")
    //@Headers("content-type: application/json", "accept: application/json")
    fun changenick(

        //@Query("nickname") nickname: String
        @Body request: ChangenickRequest

    ): Call<UserInfoResponse>

}