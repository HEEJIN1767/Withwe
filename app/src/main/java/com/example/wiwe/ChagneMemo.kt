package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.ChangeMemoRequest
import com.example.wiwe.Api.Request.CommunityRequest
import com.example.wiwe.Api.Request.OneMemoService
import com.example.wiwe.Api.Request.ReadCommunityService
import com.example.wiwe.Api.Response.ChangeMemoResponse
import com.example.wiwe.Api.Response.OneMemoResponse
import com.example.wiwe.Api.Response.ReadCommunityResponse
import com.example.wiwe.databinding.ActivityChagneMemoBinding
import com.example.wiwe.databinding.ActivityChangeCommunityBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ChagneMemo : AppCompatActivity() {
    private lateinit var binding: ActivityChagneMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChagneMemoBinding.inflate(layoutInflater)
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

        val Service = retrofit.create(OneMemoService::class.java)

        val memoId = intent.getLongExtra("memoId", 0)
        //메모 단건조회
        Service.oneMemo(memoId)
            .enqueue(object : Callback<OneMemoResponse> {

                override fun onResponse(
                    call: Call<OneMemoResponse>,
                    response: Response<OneMemoResponse>
                ) {
                    println(response)

                    if (response.isSuccessful) {

                        val result = response.body()
                        Log.e("조회 완료", "${result}")
                        intent.putExtra("memoId", memoId)
                        // binding.timeTv.setText(result?.result?.data?.memoId.toString())

                        binding.titleEt.setText(result?.result?.data?.memoTitle)
                        binding.contentEt1.setText(result?.result?.data?.memoContent1)
                        binding.contentEt2.setText(result?.result?.data?.memoContent2)


                    } else {
                        Log.d("조회", "실패")

                        Toast.makeText(this@ChagneMemo, "조회 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OneMemoResponse>, t: Throwable) {
                    Log.e("연결실패", t.message.toString())
                }

            })

        //메모 수정하기
        binding.MemoChangeButton.setOnClickListener {

            val memoTitle = binding.titleEt.text.toString()//아이디
            val memoContent1 = binding.contentEt1.text.toString()//이름
            val memoContent2 = binding.contentEt2.text.toString()//이름
            val data = ChangeMemoRequest(memoTitle, memoContent1, memoContent2)

            Service.changeMemoResult(memoId, data)
                .enqueue(object : Callback<ChangeMemoResponse> {
                    override fun onResponse(
                        call: Call<ChangeMemoResponse>,
                        response: Response<ChangeMemoResponse>
                    ) {
                        if (response.isSuccessful) {
                            val result = response.body()
                            Log.e("변경 완료", "${result}")
                            Toast.makeText(this@ChagneMemo, "변경완료", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@ChagneMemo, Calendar::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Log.d("변경", "실패")
                            Toast.makeText(this@ChagneMemo, "변경 실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ChangeMemoResponse>, t: Throwable) {
                        Log.e("연결실패", t.message.toString())
                    }

                })
        }
    }
}