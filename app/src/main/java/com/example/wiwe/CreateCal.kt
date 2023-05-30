package com.example.wiwe

import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.CommunityRequest
import com.example.wiwe.Api.Request.CommunityService
import com.example.wiwe.Api.Request.CreateCalRequest
import com.example.wiwe.Api.Request.CreateCalService
import com.example.wiwe.Api.Response.CommunityResponse
import com.example.wiwe.Api.Response.CreateCalResponse
import com.example.wiwe.databinding.ActivityCreateCalBinding
import com.example.wiwe.databinding.ActivityDeleteuserBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoField
import java.util.*
import java.util.Calendar
import java.util.concurrent.TimeUnit

class CreateCal : AppCompatActivity() {
    private lateinit var binding: ActivityCreateCalBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityCreateCalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        var memoDate = ""

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

        val Service = retrofit.create(CreateCalService::class.java)

        //날짜선택
        binding.calendarButton.setOnClickListener {
            val today = GregorianCalendar()
            val year: Int = today.get(Calendar.YEAR)
            val month: Int = today.get(Calendar.MONTH)
            val date: Int = today.get(Calendar.DATE)

            val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    var mon = ""
                    var day = ""
                    day = if (dayOfMonth < 10) {
                        "0${dayOfMonth}"
                    } else {
                        "${dayOfMonth}"
                    }

                    mon = if (month + 1 < 10) {
                        "0${month+1}"
                    } else {
                        "${month+1}"
                    }

                    memoDate = "${year}-"+ mon + "-" + day

                    binding.setDate.setText("${year} - "+ mon + " - " + day)
                }
            }, year, month, date)

            dlg.show()
        }


        binding.regButton.setOnClickListener {
           // val localDate:LocalDate = LocalDate.now()
            //Log.d("local", localDate.toString())

            val memoTitle = binding.titleEt.text.toString()//제목
            val memoContent1 = binding.contentEt1.text.toString()//내용1
            val memoContent2 = binding.contentEt2.text.toString()//내용2
           // val memoDate = localDate.toString()

            val data = CreateCalRequest(memoTitle, memoContent1, memoContent2, memoDate)

            Service.CreateCal(data)
                .enqueue(object : Callback<CreateCalResponse> {

                    override fun onResponse(
                        call: Call<CreateCalResponse>,
                        response: Response<CreateCalResponse>
                    ) {
                        println(response)
                        if (response.isSuccessful) {

                            val result = response.body()
                            val memoId = result?.result?.data?.memoId

                            Log.e("메모 등록 성공","${memoId}")
                            val intent = Intent(this@CreateCal, MemoList::class.java)
                            intent.putExtra("memoId", memoId)
                            startActivity(intent)
                            finish()
                        } else {

                            Log.d("등록", "실패")
                            Toast.makeText(this@CreateCal, "다시 확인해주세요", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(call: Call<CreateCalResponse>, t: Throwable) {
                        Log.e("연결 실패", t.message.toString())
                    }


                })



        }
    }
}