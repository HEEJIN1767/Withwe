package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.Checklist1ResultService
import com.example.wiwe.Api.Request.Checklist2ResultService
import com.example.wiwe.Api.Response.Checklist1ResultResponse
import com.example.wiwe.Api.Response.Checklist2ResultResponse
import com.example.wiwe.databinding.ActivityChecklist2ResultDialogBinding
import com.example.wiwe.databinding.ActivityChecklistResultDialogBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ChecklistResultDialog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_checklist_result_dialog)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()



        val checklistSingleResult = findViewById<TextView>(R.id.checklistSingleResult)
        val checklistSingleResultNumber = findViewById<TextView>(R.id.checklistNumber1)

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

        val service1 = retrofit.create(Checklist1ResultService::class.java)

        service1.checklistResult1()
            .enqueue(object : Callback<Checklist1ResultResponse> {

                override fun onResponse(
                    call: Call<Checklist1ResultResponse>,
                    response: Response<Checklist1ResultResponse>
                ) {
                    if (response.isSuccessful) {

                        val result = response.body()
                        Log.e("조회 완료", "${result}")

                        // 조회한 결과로 텍스트 설정
                        // binding.timeTv.setText(result?.result?.data?.createdAt.toString())
                        checklistSingleResult.setText(result?.result?.data?.checklistResult)
                        checklistSingleResultNumber.setText(result?.result?.data?.checklistNumber)
                    }
                    else {
                        Toast.makeText(applicationContext, "체크리스트1을 먼저 실행해주세요", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<Checklist1ResultResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}