package com.example.efficiency_in_event_management.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.efficiency_in_event_management.databinding.ActivityEditItemBinding

class EditItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditItemBinding
    private var originalItem: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        originalItem = intent.getStringExtra("ITEM_NAME")
        binding.etEditItem.editText?.setText(originalItem)

        binding.btnUpdate.setOnClickListener {
            val updatedItemName = binding.etEditItem.editText?.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("UPDATED_ITEM", updatedItemName)
            resultIntent.putExtra("ITEM_NAME", originalItem)
            setResult(RESULT_OK, resultIntent)
            finish()
            Toast.makeText(this, "Update item", Toast.LENGTH_SHORT).show()
        }
    }
}

