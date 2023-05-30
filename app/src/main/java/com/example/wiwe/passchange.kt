package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.ChagneNicknameService
import com.example.wiwe.Api.Request.ChangepassRequest
import com.example.wiwe.Api.Request.ChangepassService
import com.example.wiwe.Api.Request.UserRegisterRequest
import com.example.wiwe.Api.Response.UserInfoResponse
import com.example.wiwe.databinding.ActivityNickchangeBinding
import com.example.wiwe.databinding.ActivityPasschangeBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class passchange : AppCompatActivity() {
    private lateinit var binding: ActivityPasschangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasschangeBinding.inflate(layoutInflater)
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
        binding.home.setOnClickListener {
            val intent = Intent(this, WiWemain::class.java)
            startActivity(intent)
        }

        binding.mypg.setOnClickListener {
            val intent = Intent(this, Mypage::class.java)
            startActivity(intent)
        }
        val Service = retrofit.create(ChangepassService::class.java)

        //닉네임 변경하기
        binding.newpwok.setOnClickListener {

            val recentPassword = binding.recentpassET.text.toString()//닉네임
            val newPassword = binding.newpassET.text.toString()//닉네임

            val data = ChangepassRequest(recentPassword,newPassword)
            Service.changepass(data)
                .enqueue(object : Callback<UserInfoResponse> {
                    override fun onResponse(
                        call: Call<UserInfoResponse>,
                        response: Response<UserInfoResponse>
                    ) {
                        if (response.isSuccessful) {
                            val result = response.body()
                            Log.e("비밀번호 변경 완료", "${result}")
                            Toast.makeText(this@passchange, "변경완료", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@passchange, WiWemain::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.d("비밀번호 변경", "실패")
                            Toast.makeText(this@passchange, "변경 실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                        Log.e("연결실패", t.message.toString())
                    }

                })
        }
    }
}