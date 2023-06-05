package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.OneHobbyResponse
import com.example.wiwe.Api.Response.OneMemoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface OneHobbyService {
    //단건 조회
    @GET("/hobby/{hobbyId}")
    fun oneHobby(
        @Path("hobbyId") hobbyId: Long
    ): Call<OneHobbyResponse>
}