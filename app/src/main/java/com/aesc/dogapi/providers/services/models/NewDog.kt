package com.aesc.dogapi.providers.services.models

data class NewDog(val breed: String, val subBreed: List<String>, var visibility: Boolean = false)