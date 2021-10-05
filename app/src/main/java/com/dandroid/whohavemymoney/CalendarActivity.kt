package com.dandroid.whohavemymoney

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CalendarActivity : AppCompatActivity() {

    private val buttonAdd by lazy{
        findViewById<Button>(R.id.buttonAdd)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        buttonAdd.setOnClickListener{
            val addIntent = Intent(this, MainActivity::class.java)
            startActivity(addIntent)
            overridePendingTransition(R.anim.fadein, R.anim.hold)

        }
    }
}