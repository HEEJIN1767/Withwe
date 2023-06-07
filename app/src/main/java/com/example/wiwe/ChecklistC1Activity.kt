package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class ChecklistC1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_c1)

        //타이틀 숨기기
        val actionBar: ActionBar?
        actionBar = supportActionBar
        actionBar?.hide()



        var checklistC1Next = findViewById<Button>(R.id.btn3_next_1)
        var checklistNumber = findViewById<EditText>(R.id.number1)



        checklistC1Next.setOnClickListener {
            try {
                val checklistNumber1 = checklistNumber.getText().toString().trim()
                val checklistNum1 = Integer.parseInt(checklistNumber1)

                var intent = Intent(applicationContext, ChecklistC2Activity::class.java)
                intent.putExtra("checklistC1",checklistNum1)
                startActivity(intent)
            } catch (e: NumberFormatException) {
                Toast.makeText(applicationContext, "숫자를 입력해주세요", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}