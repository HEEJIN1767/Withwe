package com.example.wiwe

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiwe.Adapter.ReviewlistAdapter
import com.example.wiwe.Api.Request.ReadCommunityService
import com.example.wiwe.Api.Request.ReviewCreateRequest
import com.example.wiwe.Api.Request.ReviewCreateService
import com.example.wiwe.Api.Response.*
import com.example.wiwe.databinding.ActivityReadcommunityBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 리사이클러뷰 어댑터 설정
val listItems_Comment = arrayListOf<getreview>()
val ReviewlistAdapter = ReviewlistAdapter(listItems_Comment)

class readcommunity : AppCompatActivity() {
    private lateinit var binding: ActivityReadcommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadcommunityBinding.inflate(layoutInflater)
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

        // 리사이클러 뷰 레이아웃 매니저 설정, 어댑터 추가
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = ReviewlistAdapter

        val Service = retrofit.create(ReadCommunityService::class.java)
        val Service2 = retrofit.create(ReviewCreateService::class.java)
        // 인탠트해와서 데이터 반영
        val id = intent.getLongExtra("id", 0)
       // val boardsId = intent.getLongExtra("boardsId", 0)
        val commentId = intent.getLongExtra("commentId", 0)
        //게시물 단건 조회
        Service.onecommunity(id)
            .enqueue(object : Callback<ReadCommunityResponse> {

                override fun onResponse(
                    call: Call<ReadCommunityResponse>,
                    response: Response<ReadCommunityResponse>
                ) {
                    println(response)

                    if (response.isSuccessful) {

                        val result = response.body()
                        Log.e("조회 완료", "${result}")

                        // 조회한 결과로 텍스트 설정
                        intent.putExtra("id", id)

                        // binding.timeTv.setText(result?.result?.data?.createdAt.toString())
                        binding.titleTv.setText(result?.result?.data?.boardsTitle)
                        binding.nickTv.setText(result?.result?.data?.boardsWriter)
                        binding.contentTv.setText(result?.result?.data?.boardsContent)


                    } else {
                        Log.d("조회", "실패")

                        Toast.makeText(this@readcommunity, "조회 실패", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ReadCommunityResponse>, t: Throwable) {
                    Log.e("연결실패", t.message.toString())
                }

            })
        //게시물 수정하기
        binding.communityChangeButton.setOnClickListener {
            //val id = intent.getLongExtra("id", 0)

            val intent = Intent(this@readcommunity, ChangeCommunity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
            finish()
        }


        //게시물 삭제하기
        binding.deleteButton.setOnClickListener {

            // 다이얼로그 띄우기
            AlertDialog.Builder(this@readcommunity)
                .setTitle("게시물 삭제")
                .setMessage("삭제하시겠습니까?")
                .setPositiveButton("확인", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {


                        Service.deleteResult(id)
                .enqueue(object : Callback<DeleteCommunityResponse> {
                    override fun onResponse(
                        call: Call<DeleteCommunityResponse>,
                        response: Response<DeleteCommunityResponse>
                    ) {
                        if (response.isSuccessful) {
                            val result = response.body()
                            Log.e("삭제", "${result}")
                            Toast.makeText(this@readcommunity, "삭제완료", Toast.LENGTH_SHORT).show()

                            RecyclerviewCommunityAdapter.notifyDataSetChanged() // 리사이클러뷰 갱신
                            val intent = Intent(this@readcommunity, Recyclerviewcommunity::class.java)
                            //intent.putExtra("id", id)
                            startActivity(intent)
                            finish()

                        } else {
                            Log.d("삭제", "실패")

                            Toast.makeText(this@readcommunity, "본인의 게시물이 아닙니다.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<DeleteCommunityResponse>, t: Throwable) {
                        Log.e("연결실패", t.message.toString())
                    }

                })
        }
                })
                .setNegativeButton("취소", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                    }
                })
                .create()
                .show()
        }

        //댓글 작성하기
        binding.reviewButton.setOnClickListener {

            val commentContent = binding.commentEt.text.toString()//댓글내용

            val data = ReviewCreateRequest(id,commentContent)
            Service2.createcomment(data)
                .enqueue(object : Callback<ReviewCreateResponse> {

                    override fun onResponse(
                        call: Call<ReviewCreateResponse>,
                        response: Response<ReviewCreateResponse>
                    ) {
                        println(response)
                        if (response.isSuccessful) {
                            val commentContent = binding.commentEt.setText(null)
                            val result = response.body()
                            val commentId = result?.result?.data?.commentId
                           // val commentContent = binding.commentEt.text.toString()//댓글내용
                            Log.e("댓글 등록 성공", "${result}")
                            ReviewlistAdapter.notifyDataSetChanged()
                            intent.putExtra("commentId", commentId)
                            Toast.makeText(this@readcommunity, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show()

                            //댓글 목록조회
                            Service2.reviewlist(id)
                                .enqueue(object : Callback<CommentGetListResponse> {
                                    override fun onResponse(
                                        call: Call<CommentGetListResponse>,
                                        response: Response<CommentGetListResponse>
                                    ) {
                                        if (response.isSuccessful) {
                                            val result = response.body()
                                            Log.e("댓글 조회 완료", "${result}")
                                            AddItemToList(result)
                                            ReviewlistAdapter.notifyDataSetChanged()
                                        } else {
                                            Log.d("댓글 조회", "실패")

                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<CommentGetListResponse>,
                                        t: Throwable
                                    ) {
                                        Log.e("연결실패", t.message.toString())
                                    }

                                })
                        } else {

                                Log.d("댓글 등록", "실패")
                                Toast.makeText(this@readcommunity, "다시 확인해주세요", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ReviewCreateResponse>, t: Throwable) {
                        Log.e("연결 실패", t.message.toString())
                    }


                })

        }
        //댓글 목록조회
        Service2.reviewlist(id)
            .enqueue(object : Callback<CommentGetListResponse> {
                override fun onResponse(
                    call: Call<CommentGetListResponse>,
                    response: Response<CommentGetListResponse>
                ) {
                    if (response.isSuccessful) {
                        val result = response.body()
                        Log.e("댓글 조회 완료", "${result}")
                        AddItemToList(result)
                        ReviewlistAdapter.notifyDataSetChanged()
                    } else {
                        Log.d("댓글 조회", "실패")

                    }
                }

                override fun onFailure(
                    call: Call<CommentGetListResponse>,
                    t: Throwable
                ) {
                    Log.e("연결실패", t.message.toString())
                }

            })


        // 리사이클러뷰 클릭 이벤트 //댓글 삭제하기
        ReviewlistAdapter.setItemClickListener(
            object :
                ReviewlistAdapter.OnItemClickListener {
                override fun onClick(v: View, position: Int) {
                    //val commentId = intent.getLongExtra("commentId", 0)

                    // 다이얼로그 띄우기
                    AlertDialog.Builder(this@readcommunity)
                        .setTitle("댓글 삭제")
                        .setMessage("삭제하시겠습니까?")
                        .setPositiveButton("확인", object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {

                                val commentId = listItems_Comment[position].commentId
                                Service2.deletecommentResult(commentId)
                                    .enqueue(object : Callback<DeleteCommentResponse> {
                                        override fun onResponse(
                                            call: Call<DeleteCommentResponse>,
                                            response: Response<DeleteCommentResponse>
                                        ) {
                                            if (response.isSuccessful) {
                                                val result = response.body()
                                                Log.e("댓글 삭제 성공", "${result}")
                                                Toast.makeText(this@readcommunity, "댓글 삭제 성공", Toast.LENGTH_SHORT).show()

                                                // 리사이클러뷰 갱신
                                                listItems_Comment.removeAt(position) // 리사이클러뷰에서도 삭제
                                                ReviewlistAdapter.notifyDataSetChanged() // 리사이클러뷰 갱신
                                            } else {
                                                Log.d("댓글 삭제", "실패")
                                                Toast.makeText(this@readcommunity, "본인의 댓글이 아닙니다.", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                        override fun onFailure(
                                            call: Call<DeleteCommentResponse>,
                                            t: Throwable
                                        ) {
                                            Log.e("연결실패", t.message.toString())
                                        }
                                    })
                            }

                        })
                        .setNegativeButton("취소", object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                            }
                        })
                        .create()
                        .show()
                }



            })
    }


    private fun AddItemToList(getResult: CommentGetListResponse?) {
        listItems_Comment.clear()
       // if (getResult != null) {
        for (commentList in getResult!!.result.data) {
            listItems_Comment.add(commentList)
        //}
        }
        ReviewlistAdapter.notifyDataSetChanged()
    }


}








