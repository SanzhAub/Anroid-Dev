package com.example.lab2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimalApiClient {
    private const val BASE_URL = "https://api.api-ninjas.com/"

    fun create(): AnimalApiInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(AnimalApiInterface::class.java)
    }
}
