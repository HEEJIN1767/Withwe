package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistA4Activity : AppCompatActivity() {

    private var checklistA4 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a4)

        var inIntent = intent
        var checklistA1Response = intent.getIntExtra("checklistA1",0)
        var checklistA2Response = intent.getIntExtra("checklistA2",0)
        var checklistA3Response = intent.getIntExtra("checklistA3",0)

        var checklistA4Next = findViewById<Button>(R.id.btn_next_4)
        var radioGroup4 = findViewById<RadioGroup>(R.id.rg_D)


        radioGroup4.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbd1 -> {
                    checklistA4 = 0
                }
                R.id.rbd2 -> {
                    checklistA4 = 1
                }
                R.id.rbd3 -> {
                    checklistA4 = 2
                }
                R.id.rbd4 -> {
                    checklistA4 = 3
                }
                R.id.rbd5 -> {
                    checklistA4 = 4
                }
            }
        }

        Log.d("response",checklistA1Response.toString())
        checklistA4Next.setOnClickListener {
            if (checklistA4 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistA5Activity::class.java)
                intent.putExtra("checklistA1",checklistA1Response)
                intent.putExtra("checklistA2",checklistA2Response)
                intent.putExtra("checklistA3",checklistA3Response)
                intent.putExtra("checklistA4", checklistA4)
                startActivity(intent)
            }
        }
    }
}