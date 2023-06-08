package com.example.wiwe.Api.Response

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ReadCommunityResponse(
    @SerializedName("successs")
    var successs :Boolean,
    @SerializedName("code")
    var code : Int,//에러코드 띄우기
    @SerializedName("result")
    var result: readresult?

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class readresult(
    @SerializedName("data")
    val data: oneboard?,
    @SerializedName("message")
    val message: String
)

data class oneboard(
    @SerializedName("boardsId")
    var boardsId: Long,
    @SerializedName("boardsWriter")
    var boardsWriter:String,
    @SerializedName("boardsTitle")
    var boardsTitle:String,
    @SerializedName("boardsContent")
    var boardsContent: String,
    @SerializedName("boardsDate")
    var boardsDate: String

)
