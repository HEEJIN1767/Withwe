package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.wiwe.Api.Request.LoginService
import com.example.wiwe.Api.Request.UserLoginRequest
import com.example.wiwe.databinding.ActivityMainBinding

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
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        //네트워크 타임아웃 시간 정하기
        //connectionTimeout : 10초
        //Retrofit이 설정된 연결 시간 제한 내에서 서버에 연결할 수없는 경우 해당 요청을 실패한 것으로 계산
        val client = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.SECONDS)//읽기시간 초과, 연결이 설정되는 모든 바이트가 전송되는 속도를 감시
            //서버로부터의 응답까지의 시간이 읽기 시간 초과보다 크면 요청이 실패된 것을 계산
            .writeTimeout(10, TimeUnit.SECONDS).build()//쓰기 타임 아웃
        //얼마나 빨리 서버에 바이트를 보낼 수 있는지 확인


        val retrofit = Retrofit.Builder()
            .baseUrl("http://13.209.135.53:8080/")//서버주소 작성
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()

        val Service = retrofit.create(LoginService::class.java)

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etId.text.toString()
            val password = binding.etPw.text.toString()
            val data = UserLoginRequest(username, password)
            Service.requestLgoin(data)
                .enqueue(object : Callback<LoginResponse> {

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {


                            Log.e("로그인 ", "성공")

                            // 받아온 jwt 토큰 가져와서 preference에 저장
                            //로그인 성공 시 서버로부터 받은 JWT token을 sharedPreferences에 저장하고 메인화면으로 넘어감
                            //JWT token은 이후 API 호출 시 서버에서 사용자 인증을 할 때 사용
                            val result = response.body()
                           val token = result?.result?.data?.accessToken
                            val sharedPreference = getSharedPreferences("token", MODE_PRIVATE) //MODE_PRIVATE(앱에서만 접근 가능) // MODE_WORLD_READABLE/MODE_WORLD_WRITEABLE(WORLD의 keyword가 속해있는 NODE는 다른 앱에서 접근이 가능)
                            val editor = sharedPreference.edit()
                            editor.putString("jwt", token.toString())//jwt라는 key값으로
                            editor.apply()
                            //안드로이드의 SharedPreferences.Editor에는 비슷한 역할을 하는 apply()와 commit() 두 개의 메소드가 존재
                            //저장공간에 지속적인 동기를 유지하며 preferences를 작성하는 commit()과는 달리, apply()는 메모리 내의 SharedPreferences를 즉시 변경하지만 디스크로의 반영은 비동기적으로 시작되며 작업 실패에 대한 어떠한 알림도 받을 수 없다.
                           //만약 반환값을 필요로 하지 않는다면 apply()를 써도됨

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

