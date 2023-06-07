package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class ChecklistB6Activity : AppCompatActivity() {

    private var checklistB6 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_b6)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        var inIntent = intent
        var checklistB1Response = intent.getIntExtra("checklistB1",0)
        var checklistB2Response = intent.getIntExtra("checklistB2",0)
        var checklistB3Response = intent.getIntExtra("checklistB3",0)
        var checklistB4Response = intent.getIntExtra("checklistB4",0)
        var checklistB5Response = intent.getIntExtra("checklistB5",0)

        var checklistB6Next = findViewById<Button>(R.id.btn2_next_6)
        var radioGroupB6 = findViewById<RadioGroup>(R.id.rg2_F)

        radioGroupB6.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb2f1 -> {
                    checklistB6 = 0
                }
                R.id.rb2f2 -> {
                    checklistB6 = 1
                }
            }
        }

        checklistB6Next.setOnClickListener {
            if (checklistB6 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistB7Activity::class.java)
                intent.putExtra("checklistB1",checklistB1Response)
                intent.putExtra("checklistB2",checklistB2Response)
                intent.putExtra("checklistB3",checklistB3Response)
                intent.putExtra("checklistB4",checklistB4Response)
                intent.putExtra("checklistB5",checklistB5Response)
                intent.putExtra("checklistB6",checklistB6)
                startActivity(intent)
            }
        }
    }
}