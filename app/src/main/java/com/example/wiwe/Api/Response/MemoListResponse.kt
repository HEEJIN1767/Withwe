package com.example.wiwe.Api.Response

import com.example.wiwe.MemoList

data class MemoListResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: MemoListResult

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)

data class MemoListResult(

    val data: List<MemoListData>,
    val message: String
)

data class MemoListData(
    var memoId: Long,
    var title:String,
    var memoDatetime: String

)
