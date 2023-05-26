package com.example.wiwe.Api.Response

data class CalendarMemoListResponse(
    var successs :Boolean,
    var code : Int,//에러코드 띄우기
    var result: CalendarMemoResult

//이 변수명이 서버에서 json에 있는 key값과 같아야함
)

data class CalendarMemoResult(

    val data: List<CalendarMemoListData>,
    val message: String
)

data class CalendarMemoListData(
    var memoId: Long,
    var title:String,
    var memoDatetime: String
)