package com.example.lab2

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface AnimalApiService {
    @GET("animals")
    suspend fun getAnimals(
        @Header("X-Api-Key") apiKey: String,
        @Query("name") name: String
    ): List<AnimalItem>

    companion object {
        private const val BASE_URL = "https://api-ninjas.com/api/"
        private const val API_KEY = "YOUR_API_KEY"

        fun create(): AnimalApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(AnimalApiService::class.java)
        }
    }
}