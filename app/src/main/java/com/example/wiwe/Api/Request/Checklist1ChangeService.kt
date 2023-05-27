package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.CalendarMemoListResponse
import com.example.wiwe.Api.Response.Checklist1ChangeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PUT

interface Checklist1ChangeService {

    @PUT("/checklist/change1")
    @Headers("content-type: application/json", "accept: application/json")
    fun changeChecklist1(
        @Body request: Checklist1ChangeRequest
    ): Call<Checklist1ChangeResponse>
}