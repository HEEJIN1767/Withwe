package com.example.wiwe.Api.Response

data class DeleteMemoResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: DeleteMemoResult?

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class DeleteMemoResult(

    val message: String
)