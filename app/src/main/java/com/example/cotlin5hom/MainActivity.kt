package com.example.cotlin5hom

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cotlin5hom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    private val getContent =
        this.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding?.referat?.setImageURI(uri)
        }

    private var numberCounter = 0
    private var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        if (savedInstanceState != null) {
            numberCounter = savedInstanceState.getInt(NUMBER_COUNTER_KEY)
        }

        binding?.zero?.text = numberCounter.toString()
        setupCounter()
    }

    private fun openGallery() {
        getContent.launch("image/*")
    }

    private fun setupCounter() = with(binding!!) {
        button2.setOnClickListener {
            zero.text = (++numberCounter).toString()
            if (numberCounter == 10) {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(intent)
            }
        }
        button1.setOnClickListener {
            if (numberCounter > 0) {
                zero.text = (--numberCounter).toString()
            }
        }

        referat.setOnClickListener {
            openGallery()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(NUMBER_COUNTER_KEY, numberCounter)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        numberCounter = savedInstanceState.getInt(NUMBER_COUNTER_KEY)
    }

    companion object {
        const val NUMBER_COUNTER_KEY = "counter"
    }
}