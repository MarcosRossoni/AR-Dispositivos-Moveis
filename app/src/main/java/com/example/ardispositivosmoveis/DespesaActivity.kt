package com.example.ardispositivosmoveis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ardispositivosmoveis.databinding.ActivityDespesaBinding

class DespesaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDespesaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDespesaBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}