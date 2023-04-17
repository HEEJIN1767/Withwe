package com.example.wiwe

import retrofit2.Call
import retrofit2.http.*


interface LoginService {


    //@FormUrlEncoded //레트로핏에서 @Field는  @FormUrlEncoded  와 함께 쓰인다.
    @POST("/auth/sign-in")
    @Headers("content-type: application/json", "accept: application/json")
    //서비스 url
    fun requestLgoin(//인풋을 정의
        //이름이 서버에서 만드는 이름과 같아야함
     // @Field("password") password:String,//@Field는는 인자를 form-urlencoded 으로 보낼때 사용
      //@Field("username") username:String//여기서 form-urlencoded는 key=value&key=value 와 같은 형태로 데이터를 전달하는 것

       @Body request: UserLoginRequest


    ) : Call<LoginResponse> //아웃풋을 정의하는곳
}