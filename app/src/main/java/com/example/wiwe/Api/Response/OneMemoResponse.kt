package com.example.wiwe.Api.Response

import com.google.gson.annotations.SerializedName

data class OneMemoResponse(

    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: OneMemoResult?

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)
data class OneMemoResult(

    val data: OneMemoData?,
    val message: String
)

data class  OneMemoData(

    var memoId: Long,
    var memoTitle:String,
    var memoContent1: String,
    var memoContent2: String,
    var memoDatetime: String

)