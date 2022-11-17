package com.example.clock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.clock.databinding.ActivityMainBinding

class Starting_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)

        val setalarm=findViewById<Button>(R.id.setalarm)
        val stopwtch=findViewById<Button>(R.id.stopwatch)

        setalarm.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
        stopwtch.setOnClickListener{
            val intent =Intent(this,Timer::class.java)
            startActivity(intent)
        }

    }
}