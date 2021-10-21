package com.aesc.dogapi.providers.services.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aesc.dogapi.providers.services.models.Dogs
import com.aesc.dogapi.providers.services.repository.MainRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main



class MainViewModel(application: Application) : AndroidViewModel(application) {

    val errorMessage = MutableLiveData<String>()
    val responseDogs = MutableLiveData<Dogs>()
    private var job: CompletableJob? = null
    val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllDogs() {
        loading.value = true
        job = Job()
        job.let { theJob ->
            CoroutineScope(Dispatchers.IO + theJob!! + exceptionHandler).launch {
                try {
                    val response = MainRepository().dogs()
                    withContext(Main) {
                        if (response.isSuccessful) {
                            loading.value = false
                            responseDogs.postValue(response.body())
                            theJob.complete()
                        } else {
                            val msg = response.message()
                            onError(msg)
                        }
                    }
                } catch (t: Throwable) {
                    val msg = t.message.toString()
                    onError(msg)
                }
            }
        }
    }

    private fun onError(message: String) {
        try {
            loading.value = false
            errorMessage.value = message
        } catch (ex: Throwable) {
            println("Catching ex in runFailingCoroutine(): $ex")
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
