package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.ReadCommunityResponse
import com.example.wiwe.Api.Response.RecyclerviewcommunityResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchCommunityService{

    //검색조회
    @GET("/boards/boards/search")
    fun Searchcommunity(
        @Query("keyword") keyword: String


    ): Call<RecyclerviewcommunityResponse>

}