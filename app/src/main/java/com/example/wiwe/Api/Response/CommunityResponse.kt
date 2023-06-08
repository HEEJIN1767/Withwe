package com.example.wiwe.Api.Response

import com.google.gson.annotations.SerializedName

data class CommunityResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: Result?

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class Result(
    val data:createboard?,
    val message: String
)

data class createboard(

    var boardsId: Long,
    var boardsTitle:String,
    var boardsContent: String,
    var boardsDate: String
)
