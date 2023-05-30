package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.Checklist3ResultResponse
import retrofit2.Call
import retrofit2.http.GET

interface Checklist3ResultService {

    @GET("/checklist/result3")
    fun checklistResult3(): Call<Checklist3ResultResponse>
}