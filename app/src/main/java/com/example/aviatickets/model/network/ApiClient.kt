package com.example.aviatickets.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "YOUR_BASE_URL"  // Replace with your actual base URL

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create a service interface
    fun <T> createService(serviceClass: Class<T>): T {
        // Use retrofit.create() to create the service
        return retrofit.create(serviceClass)
    }
}