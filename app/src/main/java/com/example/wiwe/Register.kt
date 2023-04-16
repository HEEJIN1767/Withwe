package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wiwe.databinding.ActivityRegisterBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    var isExistBlank = false
    var isPWSame = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).build()

        var retrofit = Retrofit.Builder()
            .baseUrl("http://15.164.60.202:8080")//서버주소 작성
                //데이터를 자동으로 컨버팅하기위한 gsonfactory사용
            .addConverterFactory(GsonConverterFactory.create())
            //.build()
            .client(client).build()

        var Service = retrofit.create(RegisterService::class.java)

        val btn_register = binding.btnRegister

        btn_register.setOnClickListener {
            val name = binding.retId.text.toString()
            val password = binding.retPw.text.toString()
            val passwordcheck = binding.retPw2.text.toString()
            val nickname = binding.etNick.text.toString()
            val username = binding.etBirth.text.toString()


            Service.register(name, password, nickname, username)
                .enqueue(object : Callback<RigisterResponse> {

                    override fun onResponse(
                        call: Call<RigisterResponse>,
                        response: Response<RigisterResponse>
                    ) {

                        val result = response.body()
                        Log.e("회원가입 성공", "${result}")
                        val intent = Intent(this@Register, MainActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<RigisterResponse>, t: Throwable) {
                        Log.e("회원가입 실패", t.message.toString())
                    }

                })

            if (name.isEmpty() || password.isEmpty() || passwordcheck.isEmpty() || nickname.isEmpty() || username.isEmpty()) {
                isExistBlank = true

                Toast.makeText(this@Register, "빈칸을 작성해주세요.", Toast.LENGTH_SHORT).show()
            } else {

                binding.pwch.setOnClickListener {
                    if (password == passwordcheck) {//비밀번호 확인 -비밀번호가 일치하면
                        isPWSame = true

                        Toast.makeText(this@Register, "비밀번호가 일치합니다.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@Register, "비밀번호가 일치하지않습니다.", Toast.LENGTH_SHORT).show()
                    }

                }

            }


        }
    }
}