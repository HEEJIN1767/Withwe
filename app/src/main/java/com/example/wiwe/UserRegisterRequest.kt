package com.example.wiwe

public final data class UserRegisterRequest(
    val name: String,
    val nickname: String,
    val password: String,
    val username: String

    //  @Field("name") name: String,
    //  @Field("nickname") nickname: String,
    // @Field("password") password: String,
    // @Field("username") username: String
)