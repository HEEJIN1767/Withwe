package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistA3Activity : AppCompatActivity() {

    private var checklistA3 : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a3)

        var inIntent = intent
        var checklistA1Response = intent.getIntExtra("checklistA1",0)
        var checklistA2Response = intent.getIntExtra("checklistA2",0)

        var checklistA3Next = findViewById<Button>(R.id.btn_next_3)
        var radioGroup3 = findViewById<RadioGroup>(R.id.rg_C)


        radioGroup3.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbc1 -> {
                    checklistA3 = 0
                }
                R.id.rbc2 -> {
                    checklistA3 = 1
                }
                R.id.rbc3 -> {
                    checklistA3 = 2
                }
                R.id.rbc4 -> {
                    checklistA3 = 3
                }
                R.id.rbc5 -> {
                    checklistA3 = 4
                }
            }
        }

        Log.d("response",checklistA1Response.toString())
        checklistA3Next.setOnClickListener {
            if (checklistA3 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistA4Activity::class.java)
                intent.putExtra("checklistA1",checklistA1Response)
                intent.putExtra("checklistA2",checklistA2Response)
                intent.putExtra("checklistA3", checklistA3)
                startActivity(intent)
            }
        }
    }
}