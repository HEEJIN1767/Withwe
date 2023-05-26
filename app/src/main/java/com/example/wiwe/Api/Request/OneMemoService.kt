package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.*
import retrofit2.Call
import retrofit2.http.*

interface OneMemoService {

    //단건 조회
    @GET("/memo/{id}")
    fun oneMemo(
        @Path("id") id: Long
    ): Call<OneMemoResponse>

    //삭제
    @DELETE("/memo/{id}")
    fun deleteMemoResult(

        @Path("id") id: Long

    ): Call<DeleteMemoResponse>

    //수정
    @PUT("/memo/{id}")
    @Headers("content-type: application/json", "accept: application/json")
    fun changeMemoResult(

        @Path("id") id: Long,
        @Body request: ChangeMemoRequest

    ): Call<ChangeMemoResponse>

}

