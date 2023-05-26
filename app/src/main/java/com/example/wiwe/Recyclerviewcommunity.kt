package com.example.wiwe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiwe.Adapter.RecyclerviewCommunityAdapter
import com.example.wiwe.Api.Request.RecyclerviewCommunityService
import com.example.wiwe.Api.Response.RecyclerviewcommunityResponse
import com.example.wiwe.Api.Response.listboard
import com.example.wiwe.databinding.ActivityRecyclerviewcommunityBinding
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 리사이클러뷰 어댑터 설정
val listItems_Community = arrayListOf<listboard>()
val RecyclerviewCommunityAdapter = RecyclerviewCommunityAdapter(listItems_Community)


class Recyclerviewcommunity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerviewcommunityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerviewcommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()



        binding.writeCm.setOnClickListener {
            val intent = Intent(this, writecommunity::class.java)
            SearchItems_Community.clear()
            startActivity(intent)

        }
        // 로그인 후 저장해둔 JWT 토큰 가져오기
        val sharedPreferences = getSharedPreferences("token", MODE_PRIVATE)
        val jwt = sharedPreferences.getString("jwt", "")

        val client = OkHttpClient.Builder()
            .addInterceptor(AddHeaderJWT(jwt.toString())) // JWT header 달아주는 interceptor 추가
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.209.135.53:8080/")//서버주소 작성
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()

        // 리사이클러 뷰 레이아웃 매니저 설정, 어댑터 추가
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = RecyclerviewCommunityAdapter
        //val Service2 = retrofit.create(SearchCommunityService::class.java)

        binding.search.setOnClickListener {
            SearchItems_Community.clear()
            val intent = Intent(this, SearchCommunity::class.java)
            startActivity(intent)


        }


        val Service = retrofit.create(RecyclerviewCommunityService::class.java)

        // 리사이클러뷰 클릭 이벤트
        RecyclerviewCommunityAdapter.setItemClickListener(
            object :
                RecyclerviewCommunityAdapter.OnItemClickListener {
                override fun onClick(v: View, position: Int) {

                    val id = listItems_Community[position].boardsId

                    ReviewlistAdapter.notifyDataSetChanged()
                    val intent = Intent(this@Recyclerviewcommunity, readcommunity::class.java)
                    intent.putExtra("id", id)
                    Log.e("게시물 id 확인", "${id}")
                    startActivity(intent)
                    finish()
                }
            })


            Service.communityList()
                .enqueue(object : Callback<RecyclerviewcommunityResponse> {
                    override fun onResponse(
                        call: Call<RecyclerviewcommunityResponse>,
                        response: Response<RecyclerviewcommunityResponse>
                    ) {
                        if (response.isSuccessful) {
                            val result = response.body()

                            Log.e("조회 완료", "${result}")

                            // intent.putExtra("id", id)
                            //리사이클러뷰에 결과 출력
                            AddItemToList(result)

                        } else {
                            Log.d("조회", "실패")
                        }
                    }

                    override fun onFailure(
                        call: Call<RecyclerviewcommunityResponse>,
                        t: Throwable
                    ) {
                        Log.e("연결실패", t.message.toString())
                    }
                })
        }

    private fun AddItemToList(createResult: RecyclerviewcommunityResponse?) {
        listItems_Community.clear()

        for (communityList in createResult!!.result.data) {
            listItems_Community.add(communityList)

        }
        RecyclerviewCommunityAdapter.notifyDataSetChanged()
    }


    // API 호출 intercept 해서 JWT 헤더에 담는 interceptor
    class AddHeaderJWT constructor(val jwt: String) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val token = "Bearer $jwt"
            val newRequest = chain.request().newBuilder().apply {
                addHeader("Authorization", token)
            }.build()
            Log.d("토큰 설정", token)
            return chain.proceed(newRequest)
        }
    }
}






