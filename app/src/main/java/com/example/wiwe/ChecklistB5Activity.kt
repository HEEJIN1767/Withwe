package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistB5Activity : AppCompatActivity() {

    private var checklistB5 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_b5)

        var inIntent = intent
        var checklistB1Response = intent.getIntExtra("checklistB1",0)
        var checklistB2Response = intent.getIntExtra("checklistB2",0)
        var checklistB3Response = intent.getIntExtra("checklistB3",0)
        var checklistB4Response = intent.getIntExtra("checklistB4",0)

        var checklistB5Next = findViewById<Button>(R.id.btn2_next_5)
        var radioGroupB5 = findViewById<RadioGroup>(R.id.rg2_E)

        radioGroupB5.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb2e1 -> {
                    checklistB5 = 0
                }
                R.id.rb2e2 -> {
                    checklistB5 = 1
                }
            }
        }

        checklistB5Next.setOnClickListener {
            if (checklistB5 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistB6Activity::class.java)
                intent.putExtra("checklistB1",checklistB1Response)
                intent.putExtra("checklistB2",checklistB2Response)
                intent.putExtra("checklistB3",checklistB3Response)
                intent.putExtra("checklistB4",checklistB4Response)
                intent.putExtra("checklistB5",checklistB5)
                startActivity(intent)
            }
        }
    }
}