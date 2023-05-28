package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.wiwe.Api.Request.ChecklistFinalResultService
import com.example.wiwe.Api.Response.ChecklistFinalResultResponse
import com.example.wiwe.databinding.ActivityChecklistFinalResultBinding
import com.example.wiwe.databinding.ActivityChecklistMainBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ChecklistFinalResultActivity : AppCompatActivity() {



    private lateinit var binding: ActivityChecklistFinalResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecklistFinalResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        val service = retrofit.create(ChecklistFinalResultService::class.java)

        val checklistUserNickname = findViewById<TextView>(R.id.userNicknameChecklistResult)
        val checklistResult1 = findViewById<TextView>(R.id.checklist1_final_result)
        val checklistResult2 = findViewById<TextView>(R.id.checklist2_final_result)
        val checklistResult3 = findViewById<TextView>(R.id.checklist3_final_result)

        service.checklistFinalResult()
            .enqueue(object : Callback<ChecklistFinalResultResponse> {

                override fun onResponse(
                    call: Call<ChecklistFinalResultResponse>,
                    response: Response<ChecklistFinalResultResponse>
                ) {
                    if (response.isSuccessful) {

                        val result = response.body()
                        Log.e("조회 완료", "${result}")

                        checklistUserNickname.setText(result?.result?.data?.userNickname)
                        checklistResult1.setText(result?.result?.data?.result)
                        checklistResult2.setText(result?.result?.data?.result2)
                        checklistResult3.setText(result?.result?.data?.result3)
                    }
                }

                override fun onFailure(call: Call<ChecklistFinalResultResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

    }

}