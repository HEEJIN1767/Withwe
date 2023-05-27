package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistB4Activity : AppCompatActivity() {

    private var checklistB4 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_b4)

        var inIntent = intent
        var checklistB1Response = intent.getIntExtra("checklistB1",0)
        var checklistB2Response = intent.getIntExtra("checklistB2",0)
        var checklistB3Response = intent.getIntExtra("checklistB3",0)

        var checklistB4Next = findViewById<Button>(R.id.btn2_next_4)
        var radioGroupB4 = findViewById<RadioGroup>(R.id.rg2_D)

        radioGroupB4.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb2d1 -> {
                    checklistB4 = 0
                }
                R.id.rb2d2 -> {
                    checklistB4 = 1
                }
            }
        }

        checklistB4Next.setOnClickListener {
            if (checklistB4 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistB5Activity::class.java)
                intent.putExtra("checklistB1",checklistB1Response)
                intent.putExtra("checklistB2",checklistB2Response)
                intent.putExtra("checklistB3",checklistB3Response)
                intent.putExtra("checklistB4",checklistB4)
                startActivity(intent)
            }
        }
    }
}