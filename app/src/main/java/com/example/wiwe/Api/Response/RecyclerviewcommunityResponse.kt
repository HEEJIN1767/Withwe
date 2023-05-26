package com.example.wiwe.Api.Response

import androidx.annotation.Keep
import com.example.wiwe.Login
import com.google.gson.annotations.SerializedName
import java.net.URL
@Keep
data class RecyclerviewcommunityResponse(

    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: recyclerviewresult

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)

data class recyclerviewresult(

    val data: List<listboard>,
    val message: String
)

data class listboard(
    var boardsId: Long,
    var title:String,
    var nickname:String,
    var boardsCreatedAt: String
)
