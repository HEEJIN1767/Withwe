package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class ChecklistA2Activity : AppCompatActivity() {

    private var checklistA2 : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a2)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        var inIntent = intent
        var checklistA1Response = intent.getIntExtra("checklistA1",0)

        var checklistA2Next = findViewById<Button>(R.id.btn_next_2)
        var radioGroup2 = findViewById<RadioGroup>(R.id.rg_B)
        var radioButton1 = findViewById<RadioButton>(R.id.rbb1)
        var radioButton2 = findViewById<RadioButton>(R.id.rbb2)
        var radioButton3 = findViewById<RadioButton>(R.id.rbb3)
        var radioButton4 = findViewById<RadioButton>(R.id.rbb4)


        radioGroup2.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbb1 -> {
                    checklistA2 = 0
                }
                R.id.rbb2 -> {
                    checklistA2 = 1
                }
                R.id.rbb3 -> {
                    checklistA2 = 2
                }
                R.id.rbb4 -> {
                    checklistA2 = 3
                }
                R.id.rbb5 -> {
                    checklistA2 = 4
                }
            }
        }

        Log.d("response",checklistA1Response.toString())
        checklistA2Next.setOnClickListener {
            if (checklistA2 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistA3Activity::class.java)
                intent.putExtra("checklistA1",checklistA1Response)
                intent.putExtra("checklistA2",checklistA2)
                startActivity(intent)
            }
        }
    }
}