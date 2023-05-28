package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistB30Activity : AppCompatActivity() {

    private var checklistB3 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_b30)

        var inIntent = intent
        var checklistB1Response = intent.getIntExtra("checklistB1",0)
        var checklistB2Response = intent.getIntExtra("checklistB2",0)

        var checklistB3Next = findViewById<Button>(R.id.btn2_next_3)
        var radioGroupB3 = findViewById<RadioGroup>(R.id.rg2_C)

        radioGroupB3.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb2c1 -> {
                    checklistB3 = 0
                }
                R.id.rb2c2 -> {
                    checklistB3 = 1
                }
            }
        }

        checklistB3Next.setOnClickListener {
            if (checklistB3 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistB4Activity::class.java)
                intent.putExtra("checklistB1",checklistB1Response)
                intent.putExtra("checklistB2",checklistB2Response)
                intent.putExtra("checklistB3",checklistB3)
                startActivity(intent)
            }
        }
    }
}