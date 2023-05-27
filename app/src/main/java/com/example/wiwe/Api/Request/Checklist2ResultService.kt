package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.Checklist2ResultResponse
import retrofit2.Call
import retrofit2.http.GET

interface Checklist2ResultService {

    @GET("/checklist/result2")
    fun checklistResult2(): Call<Checklist2ResultResponse>
}