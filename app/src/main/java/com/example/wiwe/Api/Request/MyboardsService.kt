package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.MyboardsResponse
import com.example.wiwe.Api.Response.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET

interface MyboardsService{
    //내가 쓴 글 조회
    @GET("/user/myBoards")
    fun Myboards(): Call<MyboardsResponse>
}