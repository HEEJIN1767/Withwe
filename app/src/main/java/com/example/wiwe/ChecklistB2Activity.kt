package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistB2Activity : AppCompatActivity() {

    private var checklistB2 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_b2)

        var inIntent = intent
        var checklistB1Response = intent.getIntExtra("checklistB1",0)

        var checklistB2Next = findViewById<Button>(R.id.btn2_next_2)
        var radioGroupB2 = findViewById<RadioGroup>(R.id.rg2_B)

        radioGroupB2.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb2b1 -> {
                    checklistB2 = 0
                }
                R.id.rb2b2 -> {
                    checklistB2 = 1
                }
            }
        }

        checklistB2Next.setOnClickListener {
            if (checklistB2 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistB30Activity::class.java)
                intent.putExtra("checklistB1",checklistB1Response)
                intent.putExtra("checklistB2",checklistB2)
                startActivity(intent)
            }
        }
    }
}