package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.MemoListResponse
import com.example.wiwe.Api.Response.RecyclerviewcommunityResponse
import retrofit2.Call
import retrofit2.http.GET

interface MemoListService{

    //메모 목록 조회
    @GET("/memo/memo")

    fun memoList(): Call<MemoListResponse>


}