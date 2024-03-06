package com.example.tabladeanuncionskotlin.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tabladeanuncionskotlin.R
import com.example.tabladeanuncionskotlin.databinding.ActivityEditAdsBinding

class EditAdsAct : AppCompatActivity() {
    private lateinit var binding: ActivityEditAdsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}