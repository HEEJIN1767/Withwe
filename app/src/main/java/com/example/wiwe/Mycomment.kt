package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiwe.Adapter.MyboardsAdapter
import com.example.wiwe.Adapter.MycommentAdapter
import com.example.wiwe.Adapter.ReviewlistAdapter
import com.example.wiwe.Api.Request.MycommentService
import com.example.wiwe.Api.Request.ReadCommunityService
import com.example.wiwe.Api.Response.CommentGetListResponse
import com.example.wiwe.Api.Response.MyreviewResponse
import com.example.wiwe.Api.Response.getreview
import com.example.wiwe.Api.Response.myreviewdata
import com.example.wiwe.databinding.ActivityMyreviewBinding
import com.example.wiwe.databinding.ActivityReadcommunityBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 리사이클러뷰 어댑터 설정
val listItems_myComment = arrayListOf<myreviewdata>()
val MycommentAdapter = MycommentAdapter(listItems_myComment)


class Myreview : AppCompatActivity() {
    private lateinit var binding: ActivityMyreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyreviewBinding.inflate(layoutInflater)
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

        // 리사이클러 뷰 레이아웃 매니저 설정, 어댑터 추가
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = MycommentAdapter

        binding.home.setOnClickListener {
            val intent = Intent(this, WiWemain::class.java)
            startActivity(intent)
        }

        binding.mypg.setOnClickListener {
            val intent = Intent(this, Mypage::class.java)
            startActivity(intent)
        }
        val Service = retrofit.create(MycommentService::class.java)
        // 리사이클러뷰 클릭 이벤트
        MycommentAdapter.setItemClickListener(
            object :
                MycommentAdapter.OnItemClickListener {
                override fun onClick(v: View, position: Int) {

                    val id = listItems_Community[position].boardsId
                    val intent = Intent(this@Myreview, readcommunity::class.java)
                    intent.putExtra("id", id)
                    Log.e("boardsId 확인", "${id}")
                    startActivity(intent)
                    finish()
                }
            })

        //댓글 조회하기
        Service.Mycomment()
            .enqueue(object : Callback<MyreviewResponse> {
                override fun onResponse(
                    call: Call<MyreviewResponse>,
                    response: Response<MyreviewResponse>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        Log.e("댓글 조회 완료", "${result}")
                        AddItemToList(result)
                        ReviewlistAdapter.notifyDataSetChanged()


                    } else {
                        Log.d("댓글 조회", "실패")

                    }
                }

                override fun onFailure(
                    call: Call<MyreviewResponse>,
                    t: Throwable
                ) {
                    Log.e("연결실패", t.message.toString())
                }

            })

    }

    private fun AddItemToList(getResult: MyreviewResponse?) {
        listItems_myComment.clear()
        if (getResult != null) {
            for (mycommentList in getResult!!.result.data) {
                listItems_myComment.add(mycommentList)
            }
        }
        MycommentAdapter.notifyDataSetChanged()
    }
}