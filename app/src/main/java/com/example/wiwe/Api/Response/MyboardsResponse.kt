package com.example.wiwe.Api.Response

import com.google.gson.annotations.SerializedName

data class MyboardsResponse(

    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: myboardsResult

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class myboardsResult(
    val data:List<myboardsdata>,
    val message: String
)

data class myboardsdata(

    var boardsId: Long,
    var title:String,
    var nickname: String,
    var boardsDate: String
)