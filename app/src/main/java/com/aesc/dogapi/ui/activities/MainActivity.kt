package com.aesc.dogapi.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aesc.dogapi.R
import com.aesc.dogapi.providers.services.models.NewDog
import com.aesc.dogapi.providers.services.viewmodel.MainViewModel
import com.aesc.dogapi.ui.adapters.DogAdapter
import com.aesc.dogapi.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val dogsArray: MutableList<NewDog> = mutableListOf()
    lateinit var viewModels: MainViewModel
    private lateinit var adapter: DogAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModels = ViewModelProvider(this).get(MainViewModel::class.java)
        logic()
    }

    private fun logic() {
        var status = false
        viewModels.responseDogs.observe(this, {
            if (!status) {
                it.let {
                    val listOfDogs = it.message
                    listOfDogs.forEach { entry: Map.Entry<String, List<String>> ->
                        val parseDog = NewDog(entry.key, entry.value)
                        dogsArray.add(parseDog)
                    }
                    recyclerviewInit(dogsArray)
                }
            }
        })

        viewModels.errorMessage.observe(this, {
            if (!status) {
                Utils.logsUtils("ERROR $it")
            }
        })

        viewModels.loading.observe(this, {
            status = it
        })
        viewModels.getAllDogs()
    }

    private fun recyclerviewInit(datos: MutableList<NewDog>) {
        adapter = DogAdapter()
        adapter.setCategories(datos)
        rvDogs.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvDogs.adapter = adapter
    }
}