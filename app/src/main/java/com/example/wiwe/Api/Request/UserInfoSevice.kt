package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.CommentGetListResponse
import com.example.wiwe.Api.Response.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoSevice{
    //회원정보 조회
    @GET("/user/info")
    fun UserInfo(): Call<UserInfoResponse>
}

