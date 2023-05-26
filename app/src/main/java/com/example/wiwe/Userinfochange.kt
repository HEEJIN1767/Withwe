package com.example.wiwe

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.wiwe.Api.Request.DeleteUserService
import com.example.wiwe.Api.Request.ReadCommunityService
import com.example.wiwe.Api.Response.DeleteCommentResponse
import com.example.wiwe.Api.Response.DeleteUserResponse
import com.example.wiwe.databinding.ActivityMypageBinding
import com.example.wiwe.databinding.ActivityUserinfochangeBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Userinfochange : AppCompatActivity() {
    private lateinit var binding: ActivityUserinfochangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserinfochangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        val sharedPreferences = getSharedPreferences("token", MODE_PRIVATE)
        val jwt = sharedPreferences.getString("jwt", "")


        val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(Recyclerviewcommunity.AddHeaderJWT(jwt.toString()))
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.209.135.53:8080/")//서버주소 작성
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
        val Service = retrofit.create(DeleteUserService::class.java)

        binding.change2.setOnClickListener {
            val intent = Intent(this, nickchange::class.java)
            startActivity(intent)
        }

        binding.pwch.setOnClickListener {
            val intent = Intent(this, passchange::class.java)
            startActivity(intent)
        }
        binding.out.setOnClickListener {
            val intent = Intent(this, Deleteuser::class.java)
            startActivity(intent)
                    }

}

}