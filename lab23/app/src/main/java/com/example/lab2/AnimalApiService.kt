package com.example.lab2

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.Call

interface AnimalsApiService {

    @GET("/v1/animals")
    fun searchAnimals(
        @Header("X-Api-Key") apiKey: String,
        @Query("name") name: String
    ): Call<List<AnimalItem>>

    companion object {
        const val BASE_URL = "https://api.api-ninjas.com"

        fun create(): AnimalsApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AnimalsApiService::class.java)
        }
    }
}
