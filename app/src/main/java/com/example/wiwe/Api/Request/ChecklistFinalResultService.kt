package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.ChecklistFinalResultResponse
import retrofit2.Call
import retrofit2.http.GET

interface ChecklistFinalResultService {

    @GET("/checklist/allResult")
    fun checklistFinalResult(): Call<ChecklistFinalResultResponse>
}