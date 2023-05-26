package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiwe.Adapter.MemoListAdapter
import com.example.wiwe.Adapter.RecyclerviewCommunityAdapter
import com.example.wiwe.Api.Request.MemoListService
import com.example.wiwe.Api.Request.RecyclerviewCommunityService
import com.example.wiwe.Api.Response.MemoListData
import com.example.wiwe.Api.Response.MemoListResponse
import com.example.wiwe.Api.Response.RecyclerviewcommunityResponse
import com.example.wiwe.Api.Response.listboard
import com.example.wiwe.databinding.ActivityMemoListBinding
import com.example.wiwe.databinding.ActivityRecyclerviewcommunityBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 리사이클러뷰 어댑터 설정
val listItems_Memo = arrayListOf<MemoListData>()
val MemoListAdapter = MemoListAdapter(listItems_Memo)

class MemoList : AppCompatActivity() {
    private lateinit var binding: ActivityMemoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

// 로그인 후 저장해둔 JWT 토큰 가져오기
        val sharedPreferences = getSharedPreferences("token", MODE_PRIVATE)
        val jwt = sharedPreferences.getString("jwt", "")

        val client = OkHttpClient.Builder()
            .addInterceptor(Recyclerviewcommunity.AddHeaderJWT(jwt.toString())) // JWT header 달아주는 interceptor 추가
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
        binding.recyclerView.adapter = MemoListAdapter

        val Service = retrofit.create(MemoListService::class.java)
        val memoId = intent.getLongExtra("memoId", 0)
        // 리사이클러뷰 클릭 이벤트
        MemoListAdapter.setItemClickListener(
            object :
                MemoListAdapter.OnItemClickListener {
                override fun onClick(v: View, position: Int) {

                    val memoId = listItems_Memo[position].memoId

                    val intent = Intent(this@MemoList, OnewMemo::class.java)
                    intent.putExtra("memoId", memoId)
                    Log.e("메모 id 확인", "${memoId}")
                    startActivity(intent)
                    finish()
                }
            })
        Service.memoList()
            .enqueue(object : Callback<MemoListResponse> {
                override fun onResponse(
                    call: Call<MemoListResponse>,
                    response: Response<MemoListResponse>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()

                        Log.e("조회 완료", "${result}")

                         intent.putExtra("memoId", memoId)
                        //리사이클러뷰에 결과 출력
                        AddItemToList(result)

                    } else {
                        Log.d("조회", "실패")
                    }
                }

                override fun onFailure(
                    call: Call<MemoListResponse>,
                    t: Throwable
                ) {
                    Log.e("연결실패", t.message.toString())
                }
            })
    }

    private fun AddItemToList(MemoListResult: MemoListResponse?) {
        listItems_Memo.clear()

        for (MemoList in MemoListResult!!.result.data) {
            listItems_Memo.add(MemoList)

        }
        MemoListAdapter.notifyDataSetChanged()
    }
    }
