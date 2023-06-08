package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiwe.Adapter.HobbyListAdapter
import com.example.wiwe.Api.Request.HobbyListRequest
import com.example.wiwe.Api.Request.HobbyListService
import com.example.wiwe.Api.Response.HobbyListData
import com.example.wiwe.Api.Response.HobbyListResponse
import com.example.wiwe.databinding.ActivityHobbyListBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 리사이클러뷰 어댑터 설정
val listItems_Hobby = arrayListOf<HobbyListData>()
val HobbyListAdapter = HobbyListAdapter(listItems_Hobby)

class HobbyList : AppCompatActivity() {
    private lateinit var binding: ActivityHobbyListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHobbyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        // val hobbyData1 = arrayOf("혼자", "함께", " ")
        // val hobbyData2 = arrayOf("정적", "활동적", " ")
        //val hobbyData3 = arrayOf("실내", "야외", " ")

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
        binding.recyclerView.adapter = HobbyListAdapter

        // 스피너 설정
        val spinner1 = binding.spinner1
        ArrayAdapter.createFromResource(
            this, R.array.hobby1, android.R.layout.simple_spinner_item
        ).also { adapter1 ->
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = adapter1
        }

                val spinner2 = binding.spinner2
                ArrayAdapter.createFromResource(
                    this, R.array.hobby2, android.R.layout.simple_spinner_item
                ).also { adapter2 ->
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner2.adapter = adapter2
                }

        val spinner3 = binding.spinner3
        ArrayAdapter.createFromResource(
            this, R.array.hobby3, android.R.layout.simple_spinner_item
        ).also { adapter3 ->
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner3.adapter = adapter3
        }

                val Service = retrofit.create(HobbyListService::class.java)

                binding.hobbySearch.setOnClickListener {
                    val hobbyAttribute1 = spinner1.selectedItem.toString()
                    val hobbyAttribute2 = spinner2.selectedItem.toString()
                    val hobbyAttribute3 = spinner3.selectedItem.toString()

                    val data = HobbyListRequest(hobbyAttribute1, hobbyAttribute2, hobbyAttribute3)


                    Service.hobbyList(data)
                        .enqueue(object : Callback<HobbyListResponse> {
                            override fun onResponse(
                                call: Call<HobbyListResponse>,
                                response: Response<HobbyListResponse>
                            ) {
                                if (response.isSuccessful) {
                                    val result = response.body()


                                    Log.e("조회 완료", "${result}")

                                    // intent.putExtra("id", id)
                                    //리사이클러뷰에 결과 출력
                                    AddItemToList(result)
                                    HobbyListAdapter.notifyDataSetChanged()
                                } else {
                                    Log.d("조회", "실패")
                                }
                            }

                            override fun onFailure(
                                call: Call<HobbyListResponse>,
                                t: Throwable
                            ) {
                                Log.e("연결실패", t.message.toString())
                            }
                        })
                    //  }
                    // 리사이클러뷰 클릭 이벤트
                    HobbyListAdapter.setItemClickListener(
                        object :
                            HobbyListAdapter.OnItemClickListener {
                            override fun onClick(v: View, position: Int) {

                                val hobbyId = listItems_Hobby[position].hobbyId

                                HobbyListAdapter.notifyDataSetChanged()
                                val intent = Intent(this@HobbyList, OneHobby::class.java)
                                intent.putExtra("hobbyId", hobbyId)
                                Log.e("hobbyId 확인", "${hobbyId}")
                                startActivity(intent)

                            }
                        })
                }
            }

            private fun AddItemToList(createResult: HobbyListResponse?) {
                listItems_Hobby.clear()

                for (communityList in createResult!!.result.data) {
                    listItems_Hobby.add(communityList)

                }
                HobbyListAdapter.notifyDataSetChanged()
            }

        }



