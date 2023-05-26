package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.CommunityRequest
import com.example.wiwe.Api.Request.ReadCommunityService
import com.example.wiwe.Api.Response.ReadCommunityResponse
import com.example.wiwe.databinding.ActivityChangeCommunityBinding
import com.example.wiwe.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ChangeCommunity : AppCompatActivity() {
    private lateinit var binding: ActivityChangeCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeCommunityBinding.inflate(layoutInflater)
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

        val Service = retrofit.create(ReadCommunityService::class.java)

        // 인탠트해와서 데이터 반영
        val id = intent.getLongExtra("id", 0)

        Service.onecommunity(id)
            .enqueue(object : Callback<ReadCommunityResponse> {

                override fun onResponse(
                    call: Call<ReadCommunityResponse>,
                    response: Response<ReadCommunityResponse>
                ) {
                    println(response)

                    if (response.isSuccessful) {

                        val result = response.body()
                        Log.e("조회 완료", "${result}")
                        //Log.e("조회 완료", "${id}")
                        // 조회한 결과로 텍스트 설정
                        intent.putExtra("id", id)
                        binding.titleEt.setText(result?.result?.data?.boardsTitle)
                        binding.contentEt.setText(result?.result?.data?.boardsContent)


                    } else {
                        Log.d("조회", "실패")

                        Toast.makeText(this@ChangeCommunity, "조회 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ReadCommunityResponse>, t: Throwable) {
                    Log.e("연결실패", t.message.toString())
                }

            })

        //게시물 수정하기
        binding.changeButton.setOnClickListener {

            val title = binding.titleEt.text.toString()//아이디
            val content = binding.contentEt.text.toString()//이름
            val data = CommunityRequest(title, content)

            Service.changeResult(id, data)
                .enqueue(object : Callback<ReadCommunityResponse> {
                    override fun onResponse(
                        call: Call<ReadCommunityResponse>,
                        response: Response<ReadCommunityResponse>
                    ) {
                        if (response.isSuccessful) {
                            val result = response.body()
                            Log.e("변경 완료", "${result}")
                            Toast.makeText(this@ChangeCommunity, "변경완료", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@ChangeCommunity, Recyclerviewcommunity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.d("변경", "실패")
                            Toast.makeText(this@ChangeCommunity, "변경 실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ReadCommunityResponse>, t: Throwable) {
                        Log.e("연결실패", t.message.toString())
                    }

                })
        }
    }
}