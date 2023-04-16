package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        var actionBar : ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        var retrofit = Retrofit.Builder()
            .baseUrl("http://15.164.60.202:8080")//서버주소 작성
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var Service = retrofit.create(LoginService::class.java)

        val btn_login = binding.btnLogin
        val btn_signup = binding.btnSignup
        val et_id = binding.etId
        val et_pw = binding.etPw


       btn_signup.setOnClickListener {
            var intent = Intent(this, Register :: class.java)
            startActivity(intent) }

        btn_login.setOnClickListener {
            var username = et_id.text.toString()
            var password = et_pw.text.toString()

           Service.requestLgoin(username, password).enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        var login = response.body()//code, msg
                        Log.e("로그인 완료","${login}")
                        Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, WiWemain::class.java)
                        startActivity(intent)

                    }
                    else {
                        Log.d("로그인","실패")
                        Toast.makeText(this@MainActivity, "아이디, 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("연결실패",t.message.toString())
                }


            })
        }
        }
    }

