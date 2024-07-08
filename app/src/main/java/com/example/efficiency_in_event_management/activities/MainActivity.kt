package com.example.efficiency_in_event_management.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.efficiency_in_event_management.databinding.ActivityMainBinding
import com.example.efficiency_in_event_management.adapters.ItemAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val itemList = mutableListOf<String>()
    private val adapter = ItemAdapter(itemList) { item ->
        val intent = Intent(this, EditItemActivity::class.java)
        intent.putExtra("ITEM_NAME", item)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuración del RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Listener para el botón de añadir elemento
        binding.btnAddItem.setOnClickListener {
            val intent = Intent(this, CreateItemActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CREATE)
        }
    }

    // Con este método manejamos el resultado de la actividad de creación de elemento
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CREATE && resultCode == RESULT_OK) {
            val newItem = data?.getStringExtra("NEW_ITEM")
            newItem?.let {
                itemList.add(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_CREATE = 1
    }
}
