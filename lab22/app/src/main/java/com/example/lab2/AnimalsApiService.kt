package com.example.lab2

import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface AnimalApiInterface {
    @GET("/v1/animals")
    suspend fun searchAnimals(@Query("name") name: String): Response<List<Animal>>
}
