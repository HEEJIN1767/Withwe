package com.example.wiwe

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.wiwe.Api.Request.DeleteUserRequest
import com.example.wiwe.Api.Request.DeleteUserService
import com.example.wiwe.Api.Request.ReadCommunityService
import com.example.wiwe.Api.Request.UserRegisterRequest
import com.example.wiwe.Api.Response.DeleteCommentResponse
import com.example.wiwe.Api.Response.DeleteUserResponse
import com.example.wiwe.databinding.ActivityDeleteuserBinding
import com.example.wiwe.databinding.ActivityReadcommunityBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Deleteuser : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteuserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityDeleteuserBinding.inflate(layoutInflater)
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

        binding.home.setOnClickListener {
            val intent = Intent(this, WiWemain::class.java)
            startActivity(intent)
        }
        binding.mypg.setOnClickListener {
            val intent = Intent(this, Mypage::class.java)
            startActivity(intent)
        }
        val Service = retrofit.create(DeleteUserService::class.java)

        binding.deleteuser.setOnClickListener {

            // 다이얼로그 띄우기
            AlertDialog.Builder(this@Deleteuser)
                .setTitle("계정 삭제")
                .setMessage("삭제하시겠습니까?")
                .setPositiveButton("확인", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {

                        val password = binding.passET.text.toString()//비밀번호
                        val data = DeleteUserRequest(password)
                        Service.deleteuser(data)
                            .enqueue(object : Callback<DeleteUserResponse> {
                                override fun onResponse(
                                    call: Call<DeleteUserResponse>,
                                    response: Response<DeleteUserResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        val result = response.body()
                                        Log.e("계정 삭제 성공", "${result}")
                                        RecyclerviewCommunityAdapter.notifyDataSetChanged()
                                        ReviewlistAdapter.notifyDataSetChanged()
                                        Toast.makeText(this@Deleteuser, "계정이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@Deleteuser, MainActivity::class.java)
                                        startActivity(intent)
                                        finish()

                                    } else {
                                        Log.d("계정 삭제", "실패")
                                    }
                                }
                                override fun onFailure(
                                    call: Call<DeleteUserResponse>,
                                    t: Throwable
                                ) {
                                    Log.e("연결실패", t.message.toString())
                                }
                            })
                    }
                })
                .create()
                .show()



        }
    }
}
