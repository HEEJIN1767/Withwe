package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.OneMemoService
import com.example.wiwe.Api.Request.ReadCommunityService
import com.example.wiwe.Api.Response.DeleteCommunityResponse
import com.example.wiwe.Api.Response.DeleteMemoResponse
import com.example.wiwe.Api.Response.OneMemoResponse
import com.example.wiwe.Api.Response.ReadCommunityResponse
import com.example.wiwe.databinding.ActivityOnewMemoBinding
import com.example.wiwe.databinding.ActivityReadcommunityBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class OnewMemo : AppCompatActivity() {
    private lateinit var binding: ActivityOnewMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnewMemoBinding.inflate(layoutInflater)
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
        //게시물 단건조회
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

                        binding.titleTv.setText(result?.result?.data?.memoTitle)
                        binding.contentTv1.setText(result?.result?.data?.memoContent1)
                        binding.contentTv2.setText(result?.result?.data?.memoContent2)
                        binding.timeTv.setText(result?.result?.data?.memoDatetime)

                    } else {
                        Log.d("조회", "실패")

                        Toast.makeText(this@OnewMemo, "조회 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<OneMemoResponse>, t: Throwable) {
                    Log.e("연결실패", t.message.toString())
                }

            })
        //메모 수정하기
        binding.MemoChangeButton.setOnClickListener {
            //val id = intent.getLongExtra("id", 0)

            val intent = Intent(this@OnewMemo, ChagneMemo::class.java)
            intent.putExtra("memoId", memoId)
            startActivity(intent)
            finish()
        }

        //메모 삭제하기
        binding.deleteButton.setOnClickListener {

            Service.deleteMemoResult(memoId)
                .enqueue(object : Callback<DeleteMemoResponse> {
                    override fun onResponse(
                        call: Call<DeleteMemoResponse>,
                        response: Response<DeleteMemoResponse>
                    ) {
                        if (response.isSuccessful) {
                            val result = response.body()
                            Log.e("삭제", "${result}")
                            Toast.makeText(this@OnewMemo, "삭제완료", Toast.LENGTH_SHORT).show()

                            MemoListAdapter.notifyDataSetChanged() // 리사이클러뷰 갱신
                            val intent = Intent(this@OnewMemo, Calendar::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Log.d("삭제", "실패")

                            Toast.makeText(this@OnewMemo, "변경 실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<DeleteMemoResponse>, t: Throwable) {
                        Log.e("연결실패", t.message.toString())
                    }

                })
        }
    }
}