package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.CommunityResponse
import retrofit2.Call
import retrofit2.http.*


interface CommunityService {

    @POST("/boards/create")
    @Headers("content-type: application/json", "accept: application/json")
    fun community(
        @Body request: CommunityRequest
    ): Call<CommunityResponse>


}