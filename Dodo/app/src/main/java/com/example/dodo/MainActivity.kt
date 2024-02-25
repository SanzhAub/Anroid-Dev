package com.example.dodo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var adapter: ItemsAdapter
    private var items: MutableList<Item> = mutableListOf()
    private var originalItems: List<Item> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)

        originalItems = getOriginalItems()
        items.addAll(originalItems)

        itemsList.layoutManager = LinearLayoutManager(this)
        adapter = ItemsAdapter(items, this)
        itemsList.adapter = adapter

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            if (query.isNotEmpty()) {
                filterList(query)
            } else {
                Toast.makeText(this, "Please enter a search query", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun filterList(query: String) {
        val filteredList = mutableListOf<Item>()
        val searchQuery = query.toLowerCase(Locale.getDefault())
        for (item in originalItems) {
            if (item.title.toLowerCase(Locale.getDefault()).contains(searchQuery) ||
                item.desc.toLowerCase(Locale.getDefault()).contains(searchQuery)) {
                filteredList.add(item)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No items found for \"$query\"", Toast.LENGTH_SHORT).show()
        }
        adapter.updateItems(filteredList)
    }

    private fun getOriginalItems(): List<Item> {
        return listOf(
            Item(1, "p1", "Пепперони", "Пикантная пепперони, много моцареллы, томаты и томатный соус", "от 1900 тг."),
            Item(2, "p2", "Двойной цыпленок", "Цыпленок, моцарелла, соус альфредо", "от 2100 тг."),
            Item(3, "p3", "Чоризо фреш", "Пикантные колбаски чоризо из цыпленка, зеленый перец, моцарелла, томатный соус", "от 1900 тг."),
            Item(4, "p4", "Ветчина и Сыр", "Ветчина, моцарелла и соус альфредо — просто и со вкусом", "от 2000 тг."),
            Item(5, "p5", "Карбонара", "Ветчина из цыпленка, сыры чеддер и пармезан, томаты, красный лучок, моцарелла, соус альфредо, чеснок и итальянские травы", "от 2400 тг."),
            Item(6, "p6", "Сырный цыпленок", "Цыпленок, моцарелла, сыры чеддер и пармезан, сырный соус, томаты, соус альфредо, чеснок", "от 2900 тг."),
            Item(7, "p7", "Бургер-пицца", "Сыр моцарелла, ветчина, лук красный, томаты, маринованные огурчики, соус Бургер, чеснок, томатный соус", "от 2700 тг."),
            Item(8, "p8", "Песто", "Сочный цыпленок с пикантным соусом песто, кубики брынзы, томаты, моцарелла и соус альфредо", "от 2700 тг.")
        )
    }
}
