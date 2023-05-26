package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.DeleteCommunityResponse
import com.example.wiwe.Api.Response.ReadCommunityResponse
import retrofit2.Call
import retrofit2.http.*

interface ReadCommunityService{

    //단건조회
    @GET("/boards/boards/{id}")
    fun onecommunity(
        @Path("id") id: Long

    ): Call <ReadCommunityResponse>

    //삭제
    @DELETE("/boards/boards/{id}")
    fun deleteResult(

        @Path("id") id: Long

    ): Call<DeleteCommunityResponse>

    //수정
    @PUT("/boards/boards/{id}")
    @Headers("content-type: application/json", "accept: application/json")
    fun changeResult(

        @Path("id") id: Long,
        @Body request: CommunityRequest

    ): Call<ReadCommunityResponse>

}