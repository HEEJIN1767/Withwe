package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistA6Activity : AppCompatActivity() {

    private var checklistA6 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a6)

        var inIntent = intent
        var checklistA1Response = intent.getIntExtra("checklistA1",0)
        var checklistA2Response = intent.getIntExtra("checklistA2",0)
        var checklistA3Response = intent.getIntExtra("checklistA3",0)
        var checklistA4Response = intent.getIntExtra("checklistA4",0)
        var checklistA5Response = intent.getIntExtra("checklistA5",0)

        var checklistA6Next = findViewById<Button>(R.id.btn_next_6)
        var radioGroup6 = findViewById<RadioGroup>(R.id.rg_F)


        radioGroup6.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbf1 -> {
                    checklistA6 = 0
                }
                R.id.rbf2 -> {
                    checklistA6 = 1
                }
                R.id.rbf3 -> {
                    checklistA6 = 2
                }
                R.id.rbf4 -> {
                    checklistA6 = 3
                }
                R.id.rbf5 -> {
                    checklistA6 = 4
                }
            }
        }

        Log.d("response",checklistA1Response.toString())
        checklistA6Next.setOnClickListener {
            if (checklistA6 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistA7Activity::class.java)
                intent.putExtra("checklistA1",checklistA1Response)
                intent.putExtra("checklistA2",checklistA2Response)
                intent.putExtra("checklistA3",checklistA3Response)
                intent.putExtra("checklistA4",checklistA4Response)
                intent.putExtra("checklistA5",checklistA5Response)
                intent.putExtra("checklistA6", checklistA6)
                startActivity(intent)
            }
        }
    }
}