package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiwe.Adapter.RecyclerviewCommunityAdapter
import com.example.wiwe.Adapter.SearchCommunityAdapter
import com.example.wiwe.Api.Request.RecyclerviewCommunityService
import com.example.wiwe.Api.Request.RegisterService
import com.example.wiwe.Api.Request.SearchCommunityService
import com.example.wiwe.Api.Response.RecyclerviewcommunityResponse
import com.example.wiwe.Api.Response.listboard
import com.example.wiwe.databinding.ActivityRegisterBinding
import com.example.wiwe.databinding.ActivitySearchcommunityBinding
import com.example.wiwe.databinding.ActivityWiWemainBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 리사이클러뷰 어댑터 설정
val SearchItems_Community = arrayListOf<listboard>()
val SearchCommunityAdapter = SearchCommunityAdapter(SearchItems_Community)

class SearchCommunity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchcommunityBinding

    var isExistBlank = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchcommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        // 로그인 후 저장해둔 JWT 토큰 가져오기
        val sharedPreferences = getSharedPreferences("token", MODE_PRIVATE)
        val jwt = sharedPreferences.getString("jwt", "")


        val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(Recyclerviewcommunity.AddHeaderJWT(jwt.toString()))
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.209.135.53:8080/")//서버주소 작성
            .addConverterFactory(GsonConverterFactory.create())//데이터를 자동으로 컨버팅하기위한 gsonfactory사용
            .client(client).build()

        // 리사이클러 뷰 레이아웃 매니저 설정, 어댑터 추가
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = SearchCommunityAdapter


        val Service = retrofit.create(SearchCommunityService::class.java)

        binding.search.setOnClickListener {

            //검색 키워드 가져오기
            val keyword = binding.keywordEt.text.toString()

            Service.Searchcommunity(keyword)
                .enqueue(object : Callback<RecyclerviewcommunityResponse> {
                    override fun onResponse(
                        call: Call<RecyclerviewcommunityResponse>,
                        response: Response<RecyclerviewcommunityResponse>
                    ) {
                        if (keyword.isEmpty()) {
                            isExistBlank = true
                            Log.d("빈칸", "있음")
                            Toast.makeText(this@SearchCommunity, "빈칸을 작성해주세요.", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            if (response.isSuccessful) {
                                val result = response.body()
                                Log.e("검색 완료", "${result}")
                                AddItemToList(result)
                                SearchCommunityAdapter.notifyDataSetChanged()
                            } else {
                                Log.d("검색", "실패")

                            }
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


        // 리사이클러뷰 클릭 이벤트
        SearchCommunityAdapter.setItemClickListener(
            object :
                SearchCommunityAdapter.OnItemClickListener {
                override fun onClick(v: View, position: Int) {

                    val id = SearchItems_Community[position].boardsId
                    val intent = Intent(this@SearchCommunity, readcommunity::class.java)
                    intent.putExtra("id", id)
                    Log.e("게시물 id 확인", "${id}")
                    startActivity(intent)
                    finish()
                }
            })
    }


    private fun AddItemToList(searchResult: RecyclerviewcommunityResponse?) {

        if (!searchResult?.result?.data.isNullOrEmpty()) {
            SearchItems_Community.clear()

            for (communityList in searchResult!!.result.data) {
                SearchItems_Community.add(communityList)

            }
            SearchCommunityAdapter.notifyDataSetChanged()
        } else {
            // 검색 결과 없음
            Toast.makeText(this,"검색 결과가 없습니다", Toast.LENGTH_SHORT).show()
        }

    }
}

