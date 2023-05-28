package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.Checklist1ChangeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PUT

interface Checklist3ChangeService {

    @PUT("/checklist/change3")
    @Headers("content-type: application/json", "accept: application/json")
    fun changeChecklist3(
        @Body request: Checklist3ChangeRequest
    ): Call<Checklist1ChangeResponse>
}