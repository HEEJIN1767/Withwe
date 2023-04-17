package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.wiwe.databinding.ActivityMainBinding
import com.google.firebase.messaging.FirebaseMessaging
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        var actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        var retrofit = Retrofit.Builder()
            .baseUrl("http://15.164.60.202:8080")//서버주소 작성
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var Service = retrofit.create(LoginService::class.java)

        binding.btnSignup.setOnClickListener {
            var intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            var username = binding.etId.text.toString()
            var password = binding.etPw.text.toString()
            val data = UserLoginRequest(username, password)
            Service.requestLgoin(data)
                .enqueue(object : Callback<LoginResponse> {

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                          //  var result = response.body()//code, msg
                            Log.e("로그인 ", "성공")
                            //Log.d("로그인","성공")

                            Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@MainActivity, WiWemain::class.java)
                            startActivity(intent)

                        } else {
                            Log.d("로그인", "실패")
                            Toast.makeText(this@MainActivity, "아이디, 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.e("연결실패", t.message.toString())
                    }


                })
        }
    }

}

