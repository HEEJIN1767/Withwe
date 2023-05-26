package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.MyreviewResponse
import com.example.wiwe.Api.Response.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET

interface MycommentService{
    //내가 쓴 글 조회
    @GET("/user/myComment")
    fun Mycomment(): Call<MyreviewResponse>
}