package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class ChecklistA7Activity : AppCompatActivity() {

    private var checklistA7 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a7)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        var inIntent = intent
        var checklistA1Response = intent.getIntExtra("checklistA1",0)
        var checklistA2Response = intent.getIntExtra("checklistA2",0)
        var checklistA3Response = intent.getIntExtra("checklistA3",0)
        var checklistA4Response = intent.getIntExtra("checklistA4",0)
        var checklistA5Response = intent.getIntExtra("checklistA5",0)
        var checklistA6Response = intent.getIntExtra("checklistA6",0)

        var checklistA7Next = findViewById<Button>(R.id.btn_next_7)
        var radioGroup7 = findViewById<RadioGroup>(R.id.rg_G)


        radioGroup7.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbg1 -> {
                    checklistA7 = 0
                }
                R.id.rbg2 -> {
                    checklistA7 = 1
                }
                R.id.rbg3 -> {
                    checklistA7 = 2
                }
                R.id.rbg4 -> {
                    checklistA7 = 3
                }
                R.id.rbg5 -> {
                    checklistA7 = 4
                }
            }
        }

        Log.d("response",checklistA1Response.toString())
        checklistA7Next.setOnClickListener {
            if (checklistA7 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistA8Activity::class.java)
                intent.putExtra("checklistA1",checklistA1Response)
                intent.putExtra("checklistA2",checklistA2Response)
                intent.putExtra("checklistA3",checklistA3Response)
                intent.putExtra("checklistA4",checklistA4Response)
                intent.putExtra("checklistA5",checklistA5Response)
                intent.putExtra("checklistA6",checklistA6Response)
                intent.putExtra("checklistA7", checklistA7)
                startActivity(intent)
            }
        }
    }
}