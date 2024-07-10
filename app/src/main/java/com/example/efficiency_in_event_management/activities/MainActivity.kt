package com.example.efficiency_in_event_management.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.efficiency_in_event_management.R
import com.example.efficiency_in_event_management.databinding.ActivityMainBinding
import com.example.efficiency_in_event_management.adapters.ItemAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val itemList = mutableListOf<String>()
    private lateinit var adapter: ItemAdapter

    private val createItemLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            handleCreateItemResult(data)
        }
    }

    private val editItemLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            handleEditItemResult(data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración del RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Creación del adaptador con la función lambda para eliminar un elemento
        adapter = ItemAdapter(itemList,
            onItemClick = { item ->
                startEditItemActivity(item)
            },
            onDeleteClick = { position ->
                deleteItem(position)
            }
        )
        binding.recyclerView.adapter = adapter

        // Listener para el botón de añadir elemento
        binding.btnAddItem.setOnClickListener {
            startCreateItemActivity()
        }
    }

    // Inflar el menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Tratar los clicks en los elementos del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                startCreateItemActivity()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
                // Manejar la acción de configuración
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Método para manejar el resultado de la creación de un nuevo elemento
    private fun handleCreateItemResult(data: Intent?) {
        val newItem = data?.getStringExtra("NEW_ITEM")
        newItem?.let {
            val position = itemList.size
            itemList.add(it)
            adapter.notifyItemInserted(position)
            Toast.makeText(this, "New item added", Toast.LENGTH_SHORT).show()
        }
    }

    // Método para manejar el resultado de la edición de un elemento existente
    private fun handleEditItemResult(data: Intent?) {
        val updatedItem = data?.getStringExtra("UPDATED_ITEM")
        updatedItem?.let {
            val originalItem = data.getStringExtra("ITEM_NAME")
            val index = itemList.indexOf(originalItem)
            if (index != -1) {
                itemList[index] = updatedItem
                adapter.notifyItemChanged(index)
                Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para iniciar la actividad de creación de un nuevo elemento
    private fun startCreateItemActivity() {
        val intent = Intent(this, CreateItemActivity::class.java)
        createItemLauncher.launch(intent)
    }

    // Método para iniciar la actividad de edición de un elemento existente
    private fun startEditItemActivity(item: String) {
        val intent = Intent(this, EditItemActivity::class.java)
        intent.putExtra("ITEM_NAME", item)
        editItemLauncher.launch(intent)
    }

    // Método para eliminar un elemento de la lista
    private fun deleteItem(position: Int) {
        itemList.removeAt(position)
        adapter.notifyItemRemoved(position)
        Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show()
    }
}
