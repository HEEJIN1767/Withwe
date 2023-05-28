package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.Checklist1ChangeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PUT

interface Checklist2ChangeService {

    @PUT("/checklist/change2")
    @Headers("content-type: application/json", "accept: application/json")
    fun changeChecklist2(
        @Body request: Checklist2ChangeRequest
    ): Call<Checklist1ChangeResponse>
}