package com.aesc.dogapi.providers.services.models

data class Dogs(
    val message: Map<String, List<String>>,
    val status: String
)