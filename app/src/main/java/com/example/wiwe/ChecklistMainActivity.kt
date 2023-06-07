package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.wiwe.Api.Request.*
import com.example.wiwe.Api.Response.Checklist1ResultResponse
import com.example.wiwe.Api.Response.Checklist2ResultResponse
import com.example.wiwe.Api.Response.ChecklistFinalResultResponse
import com.example.wiwe.Api.Response.UserInfoResponse
import com.example.wiwe.databinding.ActivityChecklistMainBinding
import com.example.wiwe.databinding.ActivityReadcommunityBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ChecklistMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChecklistMainBinding

    lateinit var checklistButton1: Button
    lateinit var checklistButton2: Button
    lateinit var checklistButton3: Button
    lateinit var checklistResult1: TextView
    lateinit var checklistResult2: TextView
    lateinit var checklistResult3: TextView
    lateinit var checklistFinalResult: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecklistMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()



        checklistButton1 = findViewById<Button>(R.id.checklistButton1)
        checklistButton2 = findViewById(R.id.checklistButton2)
        checklistResult1 = findViewById<TextView>(R.id.checklistResult1)
        checklistResult2 = findViewById(R.id.checklistResult2)
        checklistResult3 = findViewById(R.id.checklistResult3)
        checklistButton3 = findViewById(R.id.checklistButton3)
        checklistFinalResult = findViewById(R.id.result)


        checklistButton1.setOnClickListener {
            var intent = Intent(this@ChecklistMainActivity, ChecklistA1Activity::class.java)
            startActivity(intent)
        }
        checklistButton2.setOnClickListener {
            var intent = Intent(this@ChecklistMainActivity, ChecklistB1Activity::class.java)
            startActivity(intent)
        }

        checklistButton3.setOnClickListener {
            var intent = Intent(this@ChecklistMainActivity, ChecklistC1Activity::class.java)
            startActivity(intent)
        }

        checklistResult1.setOnClickListener {

            var intent = Intent(applicationContext, ChecklistResultDialog::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        //Log.d("Result1",checklist1ResultResponse.toString())
        //Log.d("Result2",checklist2ResultResponse.toString())
        //Log.d("Result3",checklist3ResultResponse.toString())


        checklistResult2.setOnClickListener {

            var intent = Intent(applicationContext, Checklist2ResultDialog::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        checklistResult3.setOnClickListener {

            var intent = Intent(applicationContext, Checklist3ResultDialog::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }
        checklistFinalResult.setOnClickListener {

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

            val service = retrofit.create(ChecklistFinalResultService::class.java)

            service.checklistFinalResult()
                .enqueue(object : Callback<ChecklistFinalResultResponse> {

                    override fun onResponse(
                        call: Call<ChecklistFinalResultResponse>,
                        response: Response<ChecklistFinalResultResponse>
                    ) {
                        if (response.isSuccessful) {

                            val result = response.body()
                            Log.e("조회 완료", "${result}")

                            var intent = Intent(applicationContext, ChecklistFinalResultActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            Toast.makeText(applicationContext, "체크리스트를 먼저 실행해주세요", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<ChecklistFinalResultResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }

    override fun onBackPressed() {
        var intent = Intent(applicationContext, WiWemain::class.java)
        startActivity(intent)
        finish()
        // super.onBackPressed()
    }
}

