package com.example.wiwe.Api.Request



import com.example.wiwe.Api.Response.RecyclerviewcommunityResponse
import retrofit2.Call
import retrofit2.http.*

interface RecyclerviewCommunityService{

    //게시글 목록 조회
    @GET("/boards/boards")

    fun communityList(): Call<RecyclerviewcommunityResponse>


}