package com.example.wiwe.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wiwe.Api.Response.getreview
import com.example.wiwe.Api.Response.myreviewdata
import com.example.wiwe.R

class MycommentAdapter(val itemList: ArrayList<myreviewdata>): RecyclerView.Adapter< MycommentAdapter.ViewHolder>() {

    //RecyclerView는 ViewHolder를 새로 만들어야 할 때마다 이 메서드를 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mycomment, parent, false)
        return ViewHolder(view)

    }
    //RecyclerView는 데이터 세트 크기를 가져올 때 이 메서드를 호출
    override fun getItemCount(): Int {
        return itemList.size
    }

    //RecyclerView는 ViewHolder를 데이터와 연결할 때 이 메서드를 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }



        holder.commentId.text = itemList[position].commentId.toString()
        holder.commentContent.text = itemList[position].commentContent //댓글 내용
        holder.commentWriter.text = itemList[position].commentWriter //댓글 닉네임
        holder.boardsId.text = itemList[position].commentId.toString()
        holder.commentCreatedAt.text = itemList[position].commentCreatedAt //댓글 작성시간

    }

    //레이아웃 내 View 연결
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentId: TextView = itemView.findViewById(R.id.item_number)
        val commentContent: TextView = itemView.findViewById(R.id.item_review)
        val commentWriter: TextView = itemView.findViewById(R.id.item_nickname)
        val boardsId: TextView = itemView.findViewById(R.id.item_number1)
        val commentCreatedAt: TextView = itemView.findViewById(R.id.item_time)

    }



    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener


}

