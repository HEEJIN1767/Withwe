package com.example.wiwe.Api.Response

import com.google.gson.annotations.SerializedName

data class ChangeMemoResponse(

    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: changeMemoResult?

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class changeMemoResult(
    val data: changeMemoData?,
    val message: String
)

data class changeMemoData(


    var memoId: Long,
    var memoTitle:String,
    var memoContent1: String,
    var memoContent2: String,
    var memoDatetime: String



)