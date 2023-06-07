package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class ChecklistB1Activity : AppCompatActivity() {

    private var checklistB1 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_b1)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()



        var checklistB1Next = findViewById<Button>(R.id.btn2_next_1)
        var radioGroupB1 = findViewById<RadioGroup>(R.id.rg2_A)

        radioGroupB1.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb2a1 -> {
                    checklistB1 = 0
                }
                R.id.rb2a2 -> {
                    checklistB1 = 1
                }
            }
        }

        checklistB1Next.setOnClickListener {
            if (checklistB1 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistB2Activity::class.java)
                intent.putExtra("checklistB1",checklistB1)
                startActivity(intent)
            }
        }
    }
}