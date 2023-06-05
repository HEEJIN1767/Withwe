package com.example.wiwe.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wiwe.Api.Response.HobbyListData
import com.example.wiwe.R

class HobbyListAdapter(val itemList: ArrayList<HobbyListData>): RecyclerView.Adapter<HobbyListAdapter.ViewHolder>() {

    //RecyclerView는 ViewHolder를 새로 만들어야 할 때마다 이 메서드를 호출
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hobby, parent, false)
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



        holder.hobbyId.text = itemList[position].hobbyId.toString()
        holder.hobbyTitle.text = itemList[position].hobbyTitle
        holder.hobbyAttribute1.text = "#" + itemList[position].hobbyAttribute1
        holder.hobbyAttribute2.text = "#" + itemList[position].hobbyAttribute2
        holder.hobbyAttribute3.text = "#" + itemList[position].hobbyAttribute3

    }

    //레이아웃 내 View 연결
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val hobbyId: TextView = itemView.findViewById(R.id.item_number)
        val hobbyTitle: TextView = itemView.findViewById(R.id.item_title)
        val hobbyAttribute1: TextView = itemView.findViewById(R.id.item_hobbyAttribute1)
        val hobbyAttribute2: TextView = itemView.findViewById(R.id.item_hobbyAttribute2)
        val hobbyAttribute3: TextView = itemView.findViewById(R.id.item_hobbyAttribute3)
    }



    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener


}
