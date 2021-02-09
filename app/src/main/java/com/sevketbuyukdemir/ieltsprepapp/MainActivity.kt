package com.sevketbuyukdemir.ieltsprepapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

/**
 * First activity of this application provide buttons to user for preparation test type selection
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val context : Context = this

    // Global objects for UI elements
    private lateinit var btnListening : androidx.appcompat.widget.AppCompatButton
    private lateinit var btnReading : androidx.appcompat.widget.AppCompatButton
    private lateinit var btnSpeaking : androidx.appcompat.widget.AppCompatButton
    private lateinit var btnWriting : androidx.appcompat.widget.AppCompatButton

    /**
     * Function for initialize UI elements
     */
    private fun init(){
        // Bind views with Ids
        btnListening = findViewById(R.id.btn_listening)
        btnReading = findViewById(R.id.btn_reading)
        btnSpeaking = findViewById(R.id.btn_speaking)
        btnWriting = findViewById(R.id.btn_writing)
        // Set click listener to views
        btnListening.setOnClickListener(this)
        btnReading.setOnClickListener(this)
        btnSpeaking.setOnClickListener(this)
        btnWriting.setOnClickListener(this)
    }

    /**
     * First function in Android Life Cycle
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    /**
     * Override function from View.OnClickListener for catch view clicks and perform tasks
     */
    override fun onClick(v: View?) {
        if (v != null) {
            val listeningIntent = Intent(context, ListeningActivity::class.java)
            val readingIntent = Intent(context, ReadingActivity::class.java)
            val speakingIntent = Intent(context, SpeakingActivity::class.java)
            val writingIntent = Intent(context, WritingActivity::class.java)
            when(v.id){
                R.id.btn_listening -> startActivity(listeningIntent)
                R.id.btn_reading -> startActivity(readingIntent)
                R.id.btn_speaking -> startActivity(speakingIntent)
                R.id.btn_writing -> startActivity(writingIntent)
            }
        }
    }
}