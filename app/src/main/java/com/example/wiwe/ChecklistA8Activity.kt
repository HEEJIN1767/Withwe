package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class ChecklistA8Activity : AppCompatActivity() {

    private var checklistA8 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a8)

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
        var checklistA7Response = intent.getIntExtra("checklistA7",0)

        var checklistA8Next = findViewById<Button>(R.id.btn_next_8)
        var radioGroup8 = findViewById<RadioGroup>(R.id.rg_H)


        radioGroup8.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbh1 -> {
                    checklistA8 = 0
                }
                R.id.rbh2 -> {
                    checklistA8 = 1
                }
                R.id.rbh3 -> {
                    checklistA8 = 2
                }
                R.id.rbh4 -> {
                    checklistA8 = 3
                }
                R.id.rbh5 -> {
                    checklistA8 = 4
                }
            }
        }

        Log.d("response",checklistA1Response.toString())
        checklistA8Next.setOnClickListener {
            if (checklistA8 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistA9Activity::class.java)
                intent.putExtra("checklistA1",checklistA1Response)
                intent.putExtra("checklistA2",checklistA2Response)
                intent.putExtra("checklistA3",checklistA3Response)
                intent.putExtra("checklistA4",checklistA4Response)
                intent.putExtra("checklistA5",checklistA5Response)
                intent.putExtra("checklistA6",checklistA6Response)
                intent.putExtra("checklistA7",checklistA7Response)
                intent.putExtra("checklistA8", checklistA8)
                startActivity(intent)
            }
        }
    }
}