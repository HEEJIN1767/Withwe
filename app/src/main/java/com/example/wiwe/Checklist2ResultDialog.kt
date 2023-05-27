package com.example.wiwe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import com.example.wiwe.Api.Request.Checklist1ResultService
import com.example.wiwe.Api.Request.Checklist2ResultService
import com.example.wiwe.Api.Response.Checklist2ResultResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Checklist2ResultDialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_checklist2_result_dialog)

        val checklistSingleResult2 = findViewById<TextView>(R.id.checklistSingleResult2)

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

        val service2 = retrofit.create(Checklist2ResultService::class.java)

        service2.checklistResult2()
            .enqueue(object : Callback<Checklist2ResultResponse> {

                override fun onResponse(
                    call: Call<Checklist2ResultResponse>,
                    response: Response<Checklist2ResultResponse>
                ) {
                    if (response.isSuccessful) {

                        val result = response.body()
                        Log.e("조회 완료", "${result}")

                        // 조회한 결과로 텍스트 설정
                        // binding.timeTv.setText(result?.result?.data?.createdAt.toString())
                        checklistSingleResult2.setText(result?.result?.data?.checklistResult)

                    }
                }

                override fun onFailure(call: Call<Checklist2ResultResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}