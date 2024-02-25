package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var animalRecyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var animalAdapter: AnimalAdapter

    private val animalApiService: AnimalsApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-ninjas.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(AnimalsApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animalRecyclerView = findViewById(R.id.animalRecyclerView)
        searchEditText = findViewById(R.id.searchEditText)

        animalAdapter = AnimalAdapter()
        animalRecyclerView.layoutManager = LinearLayoutManager(this)
        animalRecyclerView.adapter = animalAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                animalAdapter.filter.filter(query)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val animals = animalApiService.getAnimals()
                val myDataItems = animals.map { it.convertToMyDataItem() } // Преобразование Animal в MyDataItem
                runOnUiThread {
                    animalAdapter.setData(myDataItems)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}