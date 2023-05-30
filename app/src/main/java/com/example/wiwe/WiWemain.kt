package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.wiwe.databinding.ActivityWiWemainBinding

class WiWemain : AppCompatActivity() {
    var backKeyPressedTime : Long = 0
    private lateinit var binding: ActivityWiWemainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWiWemainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        binding.cm.setOnClickListener {
            val intent = Intent(this, Recyclerviewcommunity::class.java)
            startActivity(intent)
        }

        binding.mypg.setOnClickListener {
            val intent = Intent(this, Mypage::class.java)
            startActivity(intent)
        }

        binding.cal.setOnClickListener {
            val intent = Intent(this, Calendar::class.java)
            startActivity(intent)
        }



    }
    //뒤로 가기 막기
    override fun onBackPressed() {
        //super.onBackPressed()

        if(System.currentTimeMillis() > backKeyPressedTime + 2500){
            backKeyPressedTime = System.currentTimeMillis();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2500){
            finishAffinity()
        }
    }
}