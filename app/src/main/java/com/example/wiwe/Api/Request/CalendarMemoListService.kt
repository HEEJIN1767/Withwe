package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.CalendarMemoListResponse
import com.example.wiwe.Api.Response.CreateCalResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CalendarMemoListService {

    @POST("/memo/memo/calendar")
    @Headers("content-type: application/json", "accept: application/json")
    fun calendarMemo(
        @Body request: CalendarMemoListRequest
    ): Call<CalendarMemoListResponse>
}