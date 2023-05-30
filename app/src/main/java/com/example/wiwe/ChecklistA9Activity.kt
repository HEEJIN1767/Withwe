package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistA9Activity : AppCompatActivity() {

    private var checklistA9 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a9)

        var inIntent = intent
        var checklistA1Response = intent.getIntExtra("checklistA1",0)
        var checklistA2Response = intent.getIntExtra("checklistA2",0)
        var checklistA3Response = intent.getIntExtra("checklistA3",0)
        var checklistA4Response = intent.getIntExtra("checklistA4",0)
        var checklistA5Response = intent.getIntExtra("checklistA5",0)
        var checklistA6Response = intent.getIntExtra("checklistA6",0)
        var checklistA7Response = intent.getIntExtra("checklistA7",0)
        var checklistA8Response = intent.getIntExtra("checklistA8",0)

        var checklistA9Next = findViewById<Button>(R.id.btn_next_9)
        var radioGroup9 = findViewById<RadioGroup>(R.id.rg_I)


        radioGroup9.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbi1 -> {
                    checklistA9 = 0
                }
                R.id.rbi2 -> {
                    checklistA9 = 1
                }
                R.id.rbi3 -> {
                    checklistA9 = 2
                }
                R.id.rbi4 -> {
                    checklistA9 = 3
                }
                R.id.rbi5 -> {
                    checklistA9 = 4
                }
            }
        }

        Log.d("response",checklistA1Response.toString())
        checklistA9Next.setOnClickListener {
            if (checklistA9 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistA10Activity::class.java)
                intent.putExtra("checklistA1",checklistA1Response)
                intent.putExtra("checklistA2",checklistA2Response)
                intent.putExtra("checklistA3",checklistA3Response)
                intent.putExtra("checklistA4",checklistA4Response)
                intent.putExtra("checklistA5",checklistA5Response)
                intent.putExtra("checklistA6",checklistA6Response)
                intent.putExtra("checklistA7",checklistA7Response)
                intent.putExtra("checklistA8",checklistA8Response)
                intent.putExtra("checklistA9", checklistA9)
                startActivity(intent)
            }
        }
    }
}