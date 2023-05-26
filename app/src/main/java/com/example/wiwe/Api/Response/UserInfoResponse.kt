package com.example.wiwe.Api.Response

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class UserInfoResponse(
    @SerializedName("successs")
    var successs :Boolean,
    @SerializedName("code")
    var code : Int,//에러코드 띄우기
    @SerializedName("result")
    var result: userinfoResult?

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class userinfoResult(
    @SerializedName("data")
    val data:userinfo?,
    @SerializedName("message")
    val message: String
)

data class userinfo(
    @SerializedName("username")
    var username: String,
    @SerializedName("name")
    var name:String,
    @SerializedName("nickname")
    var nickname: String

)
//닉네임 변경, 비밀번호 변경, 회원정보