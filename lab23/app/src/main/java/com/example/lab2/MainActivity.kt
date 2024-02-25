package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab2.AnimalAdapter
import com.example.lab2.AnimalsApiService
import com.example.lab2.AnimalItem
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextSearch: EditText
    private lateinit var animalAdapter: AnimalAdapter
    private var animalList = listOf<AnimalItem>()

    private val  apiService = AnimalsApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        editTextSearch = findViewById(R.id.editTextSearch)

        recyclerView.layoutManager = LinearLayoutManager(this)
        animalAdapter = AnimalAdapter(animalList)
        recyclerView.adapter = animalAdapter

        // Listen for changes in the search EditText
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                fetchData(s.toString())
            }
        })

        // Initial fetch without any search query
        fetchData("")
    }

    private fun fetchData(query: String) {
        apiService.searchAnimals("jfhJFW6kIDMg5dauBnVTMw==lzkp9HxWPw5cB0bJ", query)
            .enqueue(object : Callback<List<AnimalItem>> {
                override fun onResponse(
                    call: Call<List<AnimalItem>>,
                    response: Response<List<AnimalItem>>
                ) {
                    if (response.isSuccessful) {
                        val animals = response.body()
                        animals?.let {
                            animalAdapter.updateData(it)
                        }
                    } else {
                        showError("Failed to fetch data: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<List<AnimalItem>>, t: Throwable) {
                    showError("Error fetching data: ${t.message}")
                }
            })
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}