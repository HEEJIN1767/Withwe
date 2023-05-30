package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.Checklist1ResultResponse
import com.example.wiwe.Api.Response.MyboardsResponse
import retrofit2.Call
import retrofit2.http.GET

interface Checklist1ResultService {

    @GET("/checklist/result1")
    fun checklistResult1(): Call<Checklist1ResultResponse>
}