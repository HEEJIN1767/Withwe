package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.HobbyListResponse
import com.example.wiwe.Api.Response.RecyclerviewcommunityResponse
import com.example.wiwe.RigisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface HobbyListService {
    @POST("/hobby/search")
    @Headers("content-type: application/json", "accept: application/json")
    fun hobbyList(
        @Body request: HobbyListRequest
    ): Call<HobbyListResponse>
}