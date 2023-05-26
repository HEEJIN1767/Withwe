package com.example.wiwe.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wiwe.Api.Response.listboard
import com.example.wiwe.R

class SearchCommunityAdapter(val itemList: ArrayList<listboard>): RecyclerView.Adapter<SearchCommunityAdapter.ViewHolder>() {

    //RecyclerView는 ViewHolder를 새로 만들어야 할 때마다 이 메서드를 호출
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_community, parent, false)
        return ViewHolder(view)

    }

    //RecyclerView는 데이터 세트 크기를 가져올 때 이 메서드를 호출
    override fun getItemCount(): Int {
        return itemList.size
    }


    override fun getItemId(i: Int): Long {
        return 0
    }
    //RecyclerView는 ViewHolder를 데이터와 연결할 때 이 메서드를 호출
    override fun onBindViewHolder(holder: SearchCommunityAdapter.ViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.itemView.setOnLongClickListener {
            itemLongClickListener.onLongClick(it, position)
            return@setOnLongClickListener true
        }
        holder.title.text = itemList[position].title
        holder.nickname.text = itemList[position].nickname
        holder.boardsId.text = itemList[position].boardsId.toString()
        //holder.boardsCreatedAt.text = itemList[position].boardsCreatedAt
    }
    //레이아웃 내 View 연결
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.item_title)
        val nickname: TextView = itemView.findViewById(R.id.item_nickname)
        val boardsId: TextView = itemView.findViewById(R.id.item_number)
        //val boardsCreatedAt: TextView = itemView.findViewById(R.id.item_time)

    }



        interface OnItemClickListener {
            fun onClick(v: View, position: Int)
        }

        interface OnItemLongClickListener {
            fun onLongClick(v: View, position: Int)
        }

        fun setItemClickListener(onItemClickListener: OnItemClickListener) {
            this.itemClickListener = onItemClickListener
        }

        fun setItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
            this.itemLongClickListener = onItemLongClickListener
        }

        private lateinit var itemClickListener: OnItemClickListener
        private lateinit var itemLongClickListener: OnItemLongClickListener
    }

