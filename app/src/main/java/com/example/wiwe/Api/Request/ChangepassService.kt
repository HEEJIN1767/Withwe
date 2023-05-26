package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.ReadCommunityResponse
import com.example.wiwe.Api.Response.UserInfoResponse
import retrofit2.Call
import retrofit2.http.*

interface ChangepassService{
    //비밀번호수정
    @PUT("/user/changePassword")
    @Headers("content-type: application/json", "accept: application/json")
    fun changepass(
        //@Query("recentPassword") recentPassword: String,
        //@Query("newPassword") newPassword: String
        @Body request: ChangepassRequest

        ): Call<UserInfoResponse>

}