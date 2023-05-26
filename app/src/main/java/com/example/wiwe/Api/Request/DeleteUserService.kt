package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.DeleteCommunityResponse
import com.example.wiwe.Api.Response.DeleteUserResponse
import retrofit2.Call
import retrofit2.http.*

interface DeleteUserService{

    //계정삭제
        @POST("/user/delete")
    @Headers("content-type: application/json", "accept: application/json")
    fun deleteuser(

        @Body request: DeleteUserRequest

    ): Call<DeleteUserResponse>
}