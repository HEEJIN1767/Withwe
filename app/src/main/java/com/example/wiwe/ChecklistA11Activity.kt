package com.example.wiwe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast

class ChecklistA11Activity : AppCompatActivity() {

    private var checklistA11 : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist_a11)

        var inIntent = intent
        var checklistA1Response = intent.getIntExtra("checklistA1",0)
        var checklistA2Response = intent.getIntExtra("checklistA2",0)
        var checklistA3Response = intent.getIntExtra("checklistA3",0)
        var checklistA4Response = intent.getIntExtra("checklistA4",0)
        var checklistA5Response = intent.getIntExtra("checklistA5",0)
        var checklistA6Response = intent.getIntExtra("checklistA6",0)
        var checklistA7Response = intent.getIntExtra("checklistA7",0)
        var checklistA8Response = intent.getIntExtra("checklistA8",0)
        var checklistA9Response = intent.getIntExtra("checklistA9",0)
        var checklistA10Response = intent.getIntExtra("checklistA10",0)

        var checklistA11Next = findViewById<Button>(R.id.btn_next_11)
        var radioGroup11 = findViewById<RadioGroup>(R.id.rg_K)


        radioGroup11.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbk1 -> {
                    checklistA11 = 0
                }
                R.id.rbk2 -> {
                    checklistA11 = 1
                }
                R.id.rbk3 -> {
                    checklistA11 = 2
                }
                R.id.rbk4 -> {
                    checklistA11 = 3
                }
                R.id.rbk5 -> {
                    checklistA11 = 4
                }
            }
        }

        Log.d("response",checklistA1Response.toString())
        checklistA11Next.setOnClickListener {
            if (checklistA11 == null) {
                Toast.makeText(applicationContext, "버튼을 선택해주세요", Toast.LENGTH_SHORT)
                    .show()
            } else {
                var intent = Intent(applicationContext, Checklist1ResultActivity::class.java)
                intent.putExtra("checklistA1",checklistA1Response)
                intent.putExtra("checklistA2",checklistA2Response)
                intent.putExtra("checklistA3",checklistA3Response)
                intent.putExtra("checklistA4",checklistA4Response)
                intent.putExtra("checklistA5",checklistA5Response)
                intent.putExtra("checklistA6",checklistA6Response)
                intent.putExtra("checklistA7",checklistA7Response)
                intent.putExtra("checklistA8",checklistA8Response)
                intent.putExtra("checklistA9",checklistA9Response)
                intent.putExtra("checklistA10",checklistA10Response)
                intent.putExtra("checklistA11", checklistA11)
                startActivity(intent)
            }
        }
    }
}