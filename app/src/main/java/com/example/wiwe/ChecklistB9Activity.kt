package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistB9Activity : AppCompatActivity() {

    private var checklistB9 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_b9)

        var inIntent = intent
        var checklistB1Response = intent.getIntExtra("checklistB1",0)
        var checklistB2Response = intent.getIntExtra("checklistB2",0)
        var checklistB3Response = intent.getIntExtra("checklistB3",0)
        var checklistB4Response = intent.getIntExtra("checklistB4",0)
        var checklistB5Response = intent.getIntExtra("checklistB5",0)
        var checklistB6Response = intent.getIntExtra("checklistB6",0)
        var checklistB7Response = intent.getIntExtra("checklistB7",0)
        var checklistB8Response = intent.getIntExtra("checklistB8",0)

        var checklistB9Next = findViewById<Button>(R.id.btn2_next_9)
        var radioGroupB9 = findViewById<RadioGroup>(R.id.rg2_I)

        radioGroupB9.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb2i1 -> {
                    checklistB9 = 0
                }
                R.id.rb2i2 -> {
                    checklistB9 = 1
                }
            }
        }

        checklistB9Next.setOnClickListener {
            if (checklistB9 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistB10Activity::class.java)
                intent.putExtra("checklistB1",checklistB1Response)
                intent.putExtra("checklistB2",checklistB2Response)
                intent.putExtra("checklistB3",checklistB3Response)
                intent.putExtra("checklistB4",checklistB4Response)
                intent.putExtra("checklistB5",checklistB5Response)
                intent.putExtra("checklistB6",checklistB6Response)
                intent.putExtra("checklistB7",checklistB7Response)
                intent.putExtra("checklistB8",checklistB8Response)
                intent.putExtra("checklistB9",checklistB9)
                startActivity(intent)
            }
        }
    }
}