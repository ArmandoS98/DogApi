package com.aesc.dogapi.providers.services.repository

import com.aesc.dogapi.providers.services.api.MyRetrofitBuilder

class MainRepository {
    suspend fun dogs() = MyRetrofitBuilder.apiService.dogs()
}