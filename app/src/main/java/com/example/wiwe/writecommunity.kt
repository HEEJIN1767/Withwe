package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.CommunityRequest
import com.example.wiwe.Api.Request.CommunityService
import com.example.wiwe.Api.Response.CommunityResponse
import com.example.wiwe.databinding.ActivityWritecommunityBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class writecommunity : AppCompatActivity() {
    //val TAG: String = "Write"
    var isExistBlank = false

    private lateinit var binding: ActivityWritecommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWritecommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // setSupportActionBar(findViewById(R.id.toolbar))


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

        val Service = retrofit.create(CommunityService::class.java)

        //게시글 작성
        binding.regButton.setOnClickListener {
            //Log.d(TAG, "게시글 작성 버튼 클릭")
            val title = binding.titleEt.text.toString()//제목
            val content = binding.contentEt.text.toString()//내용

            val data = CommunityRequest(title,content)
            Service.community(data)
                .enqueue(object : Callback<CommunityResponse> {

                    override fun onResponse(
                        call: Call<CommunityResponse>,
                        response: Response<CommunityResponse>
                    ) {
                        println(response)
                        if (response.isSuccessful) {

                            val result = response.body()
                            val id = result?.result?.data?.boardsId
                           // Log.e("게시물 등록 성공","${result}")
                            Log.e("게시물 등록 성공","${id}")
                            val intent = Intent(this@writecommunity, Recyclerviewcommunity::class.java)
                            //val intent = Intent(this@writecommunity, readcommunity::class.java)
                             //   intent.putExtra("boardsId", result?.result?.data?.boardsId)
                            intent.putExtra("id", id)
                            intent.putExtra("title", title)
                            intent.putExtra("content", content)
                                startActivity(intent)
                                finish()


                        } else {
                            if (title.isEmpty() || content.isEmpty()) {
                                isExistBlank = true
                                Log.d("빈칸", "있음")
                                Toast.makeText(this@writecommunity, "빈칸을 작성해주세요.", Toast.LENGTH_SHORT).show()

                               }else{
                                 Log.d("등록", "실패")
                                Toast.makeText(this@writecommunity, "다시 확인해주세요", Toast.LENGTH_SHORT).show()

                            }
                        }
                    }
                    override fun onFailure(call: Call<CommunityResponse>, t: Throwable) {
                        Log.e("연결 실패", t.message.toString())
                    }


                })
        }
    }
    //뒤로 가기 막기
    override fun onBackPressed() {
        //super.onBackPressed()
    }
}