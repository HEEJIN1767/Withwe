package com.example.wiwe.Api.Response

data class HobbyListResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: HobbyListResult

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class HobbyListResult(

    val data: List<HobbyListData>,
    val message: String
)

data class HobbyListData(

    var hobbyId: Long,
    var hobbyTitle: String,
    var hobbyAttribute1: String,
    var hobbyAttribute2:String,
    var hobbyAttribute3: String

)