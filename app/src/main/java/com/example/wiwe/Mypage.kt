package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.ReadCommunityService
import com.example.wiwe.Api.Request.UserInfoSevice
import com.example.wiwe.Api.Response.ReadCommunityResponse
import com.example.wiwe.Api.Response.UserInfoResponse
import com.example.wiwe.databinding.ActivityMypageBinding
import com.example.wiwe.databinding.ActivityWiWemainBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Mypage : AppCompatActivity() {
    private lateinit var binding: ActivityMypageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMypageBinding.inflate(layoutInflater)
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


        val Service = retrofit.create(UserInfoSevice::class.java)


        //게시물 단건조회
        Service.UserInfo()
            .enqueue(object : Callback<UserInfoResponse> {

                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    println(response)

                    if (response.isSuccessful) {

                        val result = response.body()
                        Log.e("조회 완료", "${result}")

                        // 조회한 결과로 텍스트 설정
                        // binding.timeTv.setText(result?.result?.data?.createdAt.toString())
                        binding.itemUsername.setText(result?.result?.data?.username)
                        binding.itemName.setText(result?.result?.data?.name)
                        binding.itemNickname.setText(result?.result?.data?.nickname)


                    } else {
                        Log.d("조회", "실패")

                        Toast.makeText(this@Mypage, "조회 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    Log.e("연결실패", t.message.toString())
                }

            })



        binding.mylist.setOnClickListener {
            val intent = Intent(this, Myboards::class.java)
            startActivity(intent)
        }

        binding.review.setOnClickListener {
            val intent = Intent(this, Myreview::class.java)
            startActivity(intent)
        }

        binding.change.setOnClickListener {
            val intent = Intent(this, Userinfochange::class.java)
            startActivity(intent)
        }

    }
}