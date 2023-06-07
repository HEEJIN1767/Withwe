package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class ChecklistC2Activity : AppCompatActivity() {

    private var checklistC2 : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_c2)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()



        var inIntent = intent
        var checklistC1Response = intent.getIntExtra("checklistC1",0)

        Log.d("response",checklistC1Response.toString())

        var checklistC2Next = findViewById<Button>(R.id.btn3_next_2)
        var radioGroupC2 = findViewById<RadioGroup>(R.id.rg3_A)

        radioGroupC2.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb3a1 -> {
                    checklistC2 = 1
                }
                R.id.rb3a2 -> {
                    checklistC2 = 2
                }
                R.id.rb3a3 -> {
                    checklistC2 = 3
                }
                R.id.rb3a4 -> {
                    checklistC2 = 4
                }
            }
        }

        checklistC2Next.setOnClickListener {
            if (checklistC2 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, Checklist3ResultActivity::class.java)
                intent.putExtra("checklistC1",checklistC1Response)
                intent.putExtra("checklistC2",checklistC2)
                startActivity(intent)
            }
        }


    }
}