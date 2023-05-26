package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.CreateCalResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CreateCalService{
    @POST("/memo/create")
    @Headers("content-type: application/json", "accept: application/json")
    fun CreateCal(
        @Body request: CreateCalRequest
    ): Call<CreateCalResponse>
}