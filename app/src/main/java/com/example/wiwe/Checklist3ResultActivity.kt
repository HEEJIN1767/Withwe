package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.Checklist2ChangeRequest
import com.example.wiwe.Api.Request.Checklist3ChangeRequest
import com.example.wiwe.Api.Request.Checklist3ChangeService
import com.example.wiwe.Api.Response.Checklist1ChangeResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Checklist3ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist3_result)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        var checklistCResult = findViewById<Button>(R.id.btn_result_c)

        var inIntent = intent
        var checklistSum3 = intent.getIntExtra("checklistC1",0) * intent.getIntExtra("checklistC2",0)

        Log.d("곱",checklistSum3.toString())

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

        val service3 = retrofit.create(Checklist3ChangeService::class.java)

        checklistCResult.setOnClickListener {

            val data = Checklist3ChangeRequest(checklistSum3)
            service3.changeChecklist3(data)
                .enqueue(object : Callback<Checklist1ChangeResponse> {
                    override fun onResponse(
                        call: Call<Checklist1ChangeResponse>,
                        response: Response<Checklist1ChangeResponse>
                    ) {
                        if (response.isSuccessful) {
                            val result = response.body()
                            Log.e("변경 완료", "${result}")
                            Toast.makeText(this@Checklist3ResultActivity, "체크리스트 저장 완료", Toast.LENGTH_SHORT).show()

                            var intent = Intent(applicationContext, ChecklistMainActivity::class.java)
                            startActivity(intent)

                        } else {
                            Log.d("변경", "실패")
                            Toast.makeText(this@Checklist3ResultActivity, "변경 실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Checklist1ChangeResponse>, t: Throwable) {
                        Log.e("연결실패", t.message.toString())
                    }

                })
        }
    }
}