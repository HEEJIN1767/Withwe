package com.example.wiwe

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiwe.Adapter.CalendarMemoListAdapter
import com.example.wiwe.Adapter.MemoListAdapter
import com.example.wiwe.Api.Request.CalendarMemoListRequest
import com.example.wiwe.Api.Request.CalendarMemoListService
import com.example.wiwe.Api.Request.MemoListService
import com.example.wiwe.Api.Response.*
import com.example.wiwe.databinding.ActivityCalendarBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.ArrayList
import java.util.Date
import java.util.concurrent.TimeUnit

// 리사이클러뷰 어댑터 설정
val listItems_CalendarMemo = arrayListOf<CalendarMemoListData>()
val CalendarMemoListAdapter = CalendarMemoListAdapter(listItems_CalendarMemo)

class Calendar : AppCompatActivity() {
    private lateinit var binding: ActivityCalendarBinding
 
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        binding.add.setOnClickListener {
            val intent = Intent(this, CreateCal::class.java)
            startActivity(intent)
        }

        binding.memolist.setOnClickListener {
            val intent = Intent(this, MemoList::class.java)
            startActivity(intent)
        }
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
        binding.recyclerView.adapter = CalendarMemoListAdapter

        val Service = retrofit.create(CalendarMemoListService::class.java)

        // 리사이클러뷰 클릭 이벤트
        CalendarMemoListAdapter.setItemClickListener(
            object :
                CalendarMemoListAdapter.OnItemClickListener {
                override fun onClick(v: View, position: Int) {

                    val memoId = listItems_CalendarMemo[position].memoId

                    val intent = Intent(this@Calendar, OnewMemo::class.java)
                    intent.putExtra("memoId", memoId)
                    Log.e("메모 id 확인", "${memoId}")
                    startActivity(intent)
                    finish()
                }
            })
       
        val memoId = intent.getLongExtra("memoId", 0)
        val localDate:LocalDate = LocalDate.now()
        val memoDate = localDate.toString()
        val data = CalendarMemoListRequest(memoDate)
        Service.calendarMemo(data)
            .enqueue(object : Callback<CalendarMemoListResponse> {
                override fun onResponse(
                    call: Call<CalendarMemoListResponse>,
                    response: Response<CalendarMemoListResponse>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        Log.e("조회 완료", "${result}")

                        intent.putExtra("memoId", memoId)
                        AddItemToList(result)
                    } else {
                        Log.d("조회", "실패")
                    }
                }

                override fun onFailure(
                    call: Call<CalendarMemoListResponse>,
                    t: Throwable
                ) {
                    Log.e("연결실패", t.message.toString())
                }
            })

        //CalendarView 날짜 변환 이벤트
        binding.cal.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                val calendar = java.util.Calendar.getInstance()
                calendar.set(p1, p2, p3)
                var mon = ""
                var day = ""
                day = if (p3 < 10){
                    "0${p3}"
                }else{
                    "${p3}"
                }

                mon = if (p2 + 1 < 10) {
                    "0${p2+1}"
                } else {
                    "${p2+1}"
                }
                val memoDate = p1.toString() + "-" + mon + "-" + day

                Log.d("hhh", memoDate)


                val data = CalendarMemoListRequest(memoDate)
                Service.calendarMemo(data)
                    .enqueue(object : Callback<CalendarMemoListResponse> {
                        override fun onResponse(
                            call: Call<CalendarMemoListResponse>,
                            response: Response<CalendarMemoListResponse>
                        ) {
                            if (response.isSuccessful) {
                                val result = response.body()
                                Log.e("조회 완료", "${result}")

                                intent.putExtra("memoId", memoId)
                                AddItemToList(result)
                            } else {
                                Log.d("조회", "실패")
                            }
                        }

                        override fun onFailure(
                            call: Call<CalendarMemoListResponse>,
                            t: Throwable
                        ) {
                            Log.e("연결실패", t.message.toString())
                        }
                    })
            }
        })

    }

    private fun AddItemToList(createResult: CalendarMemoListResponse?) {
        listItems_CalendarMemo.clear()

        for (communityList in createResult!!.result.data) {
            listItems_CalendarMemo.add(communityList)

        }
       CalendarMemoListAdapter.notifyDataSetChanged()
    }
}









