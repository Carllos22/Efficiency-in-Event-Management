package com.example.efficiency_in_event_management.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.efficiency_in_event_management.databinding.ActivityItemBinding

class CreateItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val newItem = binding.etNewItem.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("NEW_ITEM", newItem)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
