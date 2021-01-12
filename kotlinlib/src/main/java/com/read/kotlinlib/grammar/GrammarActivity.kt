package com.read.kotlinlib.grammar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.read.kotlinlib.R

class GrammarActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar)
        val father: Father = Son()
        father.talking()
    }
}