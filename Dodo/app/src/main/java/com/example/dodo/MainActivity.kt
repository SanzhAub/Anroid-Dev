package com.example.dodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.intellij.lang.annotations.Language
import java.util.*

class MainActivity : AppCompatActivity() {

    /*private lateinit var searchView: SearchView
    private lateinit var adapter: ItemsAdapter*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val itemsList: RecyclerView = findViewById(R.id.itemsList)
        val items = arrayListOf<Item>()
        /*searchView = findViewById(R.id.searchView)*/


        items.add(Item(1,"p1","Пепперони","Пикантная пепперони, много моцареллы, томаты и томатный соус","от 1900 тг."   ))
        items.add(Item(2,"p2","Двойной цыпленок","Цыпленок, моцарелла, соус альфредо","от 2100 тг."  ))
        items.add(Item(3,"p3","Чоризо фреш","Пикантные колбаски чоризо из цыпленка, зеленый перец, моцарелла, томатный соус","от 1900 тг."))
        items.add(Item(4,"p4","Ветчина и Сыр","Ветчина, моцарелла и соус альфредо — просто и со вкусом","от 2000 тг."  ))
        items.add(Item(5,"p5","Карбонара","Ветчина из цыпленка, сыры чеддер и пармезан, томаты, красный лучок, моцарелла, соус альфредо, чеснок и итальянские травы","от 2400 тг." ))
        items.add(Item(6,"p6","Сырный цыпленок","Цыпленок, моцарелла, сыры чеддер и пармезан, сырный соус, томаты, соус альфредо, чеснок","от 2900 тг."  ))
        items.add(Item(7,"p7","Бургер-пицца","Сыр моцарелла, ветчина, лук красный, томаты, маринованные огурчики, соус Бургер, чеснок, томатный соус","от 2700 тг."   ))
        items.add(Item(8,"p8","Песто","Сочный цыпленок с пикантным соусом песто, кубики брынзы, томаты, моцарелла и соус альфредо","от 2700 тг."   ))


        itemsList.layoutManager = LinearLayoutManager(this)
        itemsList.adapter = ItemsAdapter(items, this)

        /*searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })*/


    }
    /*private fun filterList(query: String?) {
        val items = arrayListOf<Item>()
        if (query != null) {
            val filteredList = arrayListOf<Item>()
            for (i in items) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No data found",Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }*/
}