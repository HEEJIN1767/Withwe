package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class ChecklistA5Activity : AppCompatActivity() {

    private var checklistA5 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a5)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()


        var inIntent = intent
        var checklistA1Response = intent.getIntExtra("checklistA1",0)
        var checklistA2Response = intent.getIntExtra("checklistA2",0)
        var checklistA3Response = intent.getIntExtra("checklistA3",0)
        var checklistA4Response = intent.getIntExtra("checklistA4",0)

        var checklistA5Next = findViewById<Button>(R.id.btn_next_5)
        var radioGroup5 = findViewById<RadioGroup>(R.id.rg_E)


        radioGroup5.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbe1 -> {
                    checklistA5 = 0
                }
                R.id.rbe2 -> {
                    checklistA5 = 1
                }
                R.id.rbe3 -> {
                    checklistA5 = 2
                }
                R.id.rbe4 -> {
                    checklistA5 = 3
                }
                R.id.rbe5 -> {
                    checklistA5 = 4
                }
            }
        }

        Log.d("response",checklistA1Response.toString())
        checklistA5Next.setOnClickListener {
            if (checklistA5 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, ChecklistA6Activity::class.java)
                intent.putExtra("checklistA1",checklistA1Response)
                intent.putExtra("checklistA2",checklistA2Response)
                intent.putExtra("checklistA3",checklistA3Response)
                intent.putExtra("checklistA4",checklistA4Response)
                intent.putExtra("checklistA5", checklistA5)
                startActivity(intent)
            }
        }
    }
}