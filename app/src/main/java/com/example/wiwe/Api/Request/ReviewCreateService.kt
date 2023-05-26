package com.example.wiwe.Api.Request

import com.example.wiwe.Api.Response.*
import retrofit2.Call
import retrofit2.http.*

interface ReviewCreateService{

    //댓글 작성
   @POST("/comment/comments")
    @Headers("content-type: application/json", "accept: application/json")
    fun createcomment(
       // @Query("boardsId") boardsId: Long,
        @Body request: ReviewCreateRequest
      // @Query("commentContent") commentContent: String
    ): Call<ReviewCreateResponse>

    //삭제
    @DELETE("/comment/comments/{commentId}")
    fun deletecommentResult(

        @Path("commentId") commentId: Long

    ): Call<DeleteCommentResponse>

    //댓글 목록조회
    @GET("/comment/comments/{id}")
    fun reviewlist(
        @Path("id") id: Long

    ): Call<CommentGetListResponse>
}