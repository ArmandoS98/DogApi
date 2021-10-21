package com.aesc.dogapi.providers.services.api

import com.aesc.dogapi.providers.services.models.Dogs
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    //GET ALL DOGS
    @GET("list/all")
    suspend fun dogs(): Response<Dogs>
}