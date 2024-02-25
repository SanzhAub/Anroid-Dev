package com.example.lab2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import com.example.lab2.AnimalItem
import com.example.lab2.Animal
import com.example.lab2.Characteristics


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AnimalAdapter

    private val animalApiInterface: AnimalApiInterface by lazy {
        AnimalApiClient.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        adapter = AnimalAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Perform initial search when activity is created
        searchAnimals("")

    }

    private fun searchAnimals(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = animalApiInterface.searchAnimals(query)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val animalList = response.body()?.map { animal ->
                            AnimalItem(
                                animal.characteristics,
                                animal.locations,
                                animal.name,
                                animal.taxonomy
                            )
                        }
                        adapter.submitAnimalList(animalList ?: emptyList())
                    } else {
                        // Handle unsuccessful response
                        handleUnsuccessfulResponse(response.code())
                    }
                }
            } catch (e: HttpException) {
                // Handle HTTP exceptions
                handleUnsuccessfulResponse(e.code())
            } catch (e: Exception) {
                e.printStackTrace()
                // Handle network error
                handleNetworkError()
            }
        }
    }

    private fun handleUnsuccessfulResponse(responseCode: Int) {
        // Handle different HTTP response codes appropriately
        // For simplicity, showing a toast message
        showToast("An error occurred. Please try again.")
    }

    private fun handleNetworkError() {
        // Display a toast message indicating network error
        showToast("Network error. Please check your internet connection and try again.")
    }

    private fun showToast(message: String) {
        // Showing toast message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
