package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.example.wiwe.Api.Request.Checklist2ResultService
import com.example.wiwe.Api.Request.Checklist3ResultService
import com.example.wiwe.Api.Response.Checklist2ResultResponse
import com.example.wiwe.Api.Response.Checklist3ResultResponse
import com.example.wiwe.databinding.ActivityChecklist2ResultDialogBinding
import com.example.wiwe.databinding.ActivityChecklist3ResultDialogBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Checklist3ResultDialog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_checklist3_result_dialog)

        val checklistSingleResult3 = findViewById<TextView>(R.id.checklistSingleResult3)
        val checklistSingleResultNumber = findViewById<TextView>(R.id.checklistNumber3)

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

        val service3 = retrofit.create(Checklist3ResultService::class.java)

        service3.checklistResult3()
            .enqueue(object : Callback<Checklist3ResultResponse> {

                override fun onResponse(
                    call: Call<Checklist3ResultResponse>,
                    response: Response<Checklist3ResultResponse>
                ) {
                    if (response.isSuccessful) {

                        val result = response.body()
                        Log.e("조회 완료", "${result}")

                        // 조회한 결과로 텍스트 설정
                        // binding.timeTv.setText(result?.result?.data?.createdAt.toString())
                        checklistSingleResult3.setText(result?.result?.data?.checklistResult)
                        checklistSingleResultNumber.setText(result?.result?.data?.checklistNumber)

                    }

                    else {
                        Toast.makeText(applicationContext, "체크리스트3을 먼저 실행해주세요", Toast.LENGTH_SHORT)
                        .show()

                    }
                }

                override fun onFailure(call: Call<Checklist3ResultResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}