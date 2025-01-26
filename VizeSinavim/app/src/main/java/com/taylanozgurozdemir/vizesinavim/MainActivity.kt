package com.taylanozgurozdemir.vizesinavim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taylanozgurozdemir.vizesinavim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
