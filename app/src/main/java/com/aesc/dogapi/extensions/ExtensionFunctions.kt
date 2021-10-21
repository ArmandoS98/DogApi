package com.aesc.dogapi.extensions

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.aesc.dogapi.R
import com.bumptech.glide.Glide

fun TextView.getSubBreeds(subBreed: List<String>) {
    var subBreedString = ""
    subBreed.let {
        it.forEach { subBreeds ->
            subBreedString += "* $subBreeds \n"
        }
    }
    if (subBreedString == "") subBreedString = "NO sub-breeds"
    this.text = subBreedString
}

fun ImageView.loadByURL(url: String) = Glide.with(this)
    .load(url)
    .placeholder(R.drawable.ic_launcher_background)
    .error(R.drawable.ic_launcher_background)
    .fallback(R.drawable.ic_launcher_background)
    .into(this)

fun ImageView.loadByResource(resource: Int) = Glide.with(this).load(resource).into(this)