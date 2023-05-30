package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.wiwe.databinding.ActivityContentsMainBinding
import com.example.wiwe.databinding.ActivityWiWemainBinding

class ContentsMain : AppCompatActivity() {
    private lateinit var binding: ActivityContentsMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContentsMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()

        binding.info.setOnClickListener {
            val intent = Intent(this, Contents1::class.java)
            startActivity(intent)
        }

        binding.home.setOnClickListener {
            val intent = Intent(this, WiWemain::class.java)
            startActivity(intent)
        }


    }
}