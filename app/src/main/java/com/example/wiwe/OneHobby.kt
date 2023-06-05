package com.example.wiwe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.OneHobbyService
import com.example.wiwe.Api.Request.OneMemoService
import com.example.wiwe.Api.Response.OneHobbyResponse
import com.example.wiwe.Api.Response.OneMemoResponse
import com.example.wiwe.databinding.ActivityOneHobbyBinding
import com.example.wiwe.databinding.ActivityOnewMemoBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OneHobby : AppCompatActivity() {
    private lateinit var binding: ActivityOneHobbyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneHobbyBinding.inflate(layoutInflater)
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

        val Service = retrofit.create(OneHobbyService::class.java)

        val hobbyId = intent.getLongExtra("hobbyId", 0)

        Service.oneHobby(hobbyId)
            .enqueue(object : Callback<OneHobbyResponse> {

                override fun onResponse(
                    call: Call<OneHobbyResponse>,
                    response: Response<OneHobbyResponse>
                ) {
                    println(response)

                    if (response.isSuccessful) {

                        val result = response.body()
                        Log.e("조회 완료", "${result}")
                       // intent.putExtra("hobbyId", hobbyId)

                        binding.hobbyTitleTv.setText(result?.result?.data?.hobbyTitle)
                       binding.hobbyAttribute1Tv.setText("#" + result?.result?.data?.hobbyAttribute1)
                        binding.hobbyAttribute2Tv.setText("#" + result?.result?.data?.hobbyAttribute2)
                       binding.hobbyAttribute3Tv.setText("#" + result?.result?.data?.hobbyAttribute3)
                        binding.hobbyContentTv1.setText(result?.result?.data?.hobbyContent)


                    } else {
                        Log.d("조회", "실패")

                        Toast.makeText(this@OneHobby, "조회 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OneHobbyResponse>, t: Throwable) {
                    Log.e("연결실패", t.message.toString())
                }

            })

    }
}