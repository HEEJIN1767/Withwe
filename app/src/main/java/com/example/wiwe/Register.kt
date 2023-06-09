package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.RegisterService
import com.example.wiwe.Api.Request.UserRegisterRequest
import com.example.wiwe.databinding.ActivityRegisterBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Register : AppCompatActivity() {

    val TAG: String = "Register"
    var isExistBlank = false
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.209.135.53:8080/")//서버주소 작성
            .addConverterFactory(GsonConverterFactory.create())//데이터를 자동으로 컨버팅하기위한 gsonfactory사용
            .client(client).build()

        val Service = retrofit.create(RegisterService::class.java)


        binding.btnRegister.setOnClickListener {
            Log.d(TAG, "회원가입 버튼 클릭")
            val username = binding.retId.text.toString()//아이디
            val name = binding.retUsername.text.toString()//이름
            val password = binding.retPw.text.toString()//비밀번호
            val nickname = binding.retNick.text.toString()//닉네임


            val data = UserRegisterRequest( username,password, name, nickname)
            Service.register(data)
                .enqueue(object : Callback<RigisterResponse> {

                    override fun onResponse(
                        call: Call<RigisterResponse>,
                        response: Response<RigisterResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.e("회원가입 ", "성공")
                            Toast.makeText(this@Register, "회원가입 성공", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Register, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            if (username.isEmpty() || name.isEmpty() || password.isEmpty() || nickname.isEmpty()) {
                                isExistBlank = true
                                Log.d("빈칸", "있음")
                                Toast.makeText(this@Register, "빈칸을 작성해주세요.", Toast.LENGTH_SHORT).show()

                            } else {
                                Log.d("회원가입", "실패")
                                Toast.makeText(this@Register, "아이디가 중복되었습니다", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                    override fun onFailure(call: Call<RigisterResponse>, t: Throwable) {
                        Log.e("연결 실패", t.message.toString())
                    }
                })
            }
        }
    }



