package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistA1Activity : AppCompatActivity() {

    private var checklistA1 : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a1)


        var checklistA1Next = findViewById<Button>(R.id.btn_next_1)
        var radioGroup1 = findViewById<RadioGroup>(R.id.rg_A)
        var radioButton1 = findViewById<RadioButton>(R.id.rbA1)
        var radioButton2 = findViewById<RadioButton>(R.id.rbA2)
        var radioButton3 = findViewById<RadioButton>(R.id.rbA3)
        var radioButton4 = findViewById<RadioButton>(R.id.rbA4)

        radioGroup1.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbA1 -> {
                    checklistA1 = 0
                }
                R.id.rbA2 -> {
                    checklistA1 = 1
                }
                R.id.rbA3 -> {
                    checklistA1 = 2
                }
                R.id.rbA4 -> {
                    checklistA1 = 3
                }
                R.id.rbA5 -> {
                    checklistA1 = 4
                }
            }
        }

        checklistA1Next.setOnClickListener {
            if (checklistA1 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistA2Activity::class.java)
                intent.putExtra("checklistA1",checklistA1)
                startActivity(intent)
            }
        }
    }
}