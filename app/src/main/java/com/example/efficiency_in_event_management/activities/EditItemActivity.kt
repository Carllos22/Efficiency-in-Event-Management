package com.example.efficiency_in_event_management.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.efficiency_in_event_management.databinding.ActivityEditItemBinding

class EditItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtenemos el nombre del elemento a ser editado
        val itemName = intent.getStringExtra("ITEM_NAME")
        binding.etEditItem.setText(itemName)

        binding.btnUpdate.setOnClickListener {
            val updatedItemName = binding.etEditItem.text.toString()
            finish()
        }
    }
}
