package com.aesc.dogapi.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.aesc.dogapi.R
import com.aesc.dogapi.extensions.getSubBreeds
import com.aesc.dogapi.providers.services.models.NewDog
import com.aesc.dogapi.utils.Utils
import kotlinx.android.synthetic.main.item_dog.view.*


class DogAdapter : RecyclerView.Adapter<DogAdapter.ViewHolder>() {

    private var datos: MutableList<NewDog> = mutableListOf()

    fun ViewGroup.inflate(@LayoutRes layoutResID: Int, attachRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutResID, this, attachRoot)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_dog, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvNombre.text = datos[position].breed
        holder.itemView.tvNombreSubbreeds.getSubBreeds(datos[position].subBreed)
        val status = datos[position].visibility
        holder.itemView.expandedLayout.visibility = if (status) VISIBLE else GONE
    }

    override fun getItemCount() = datos.size

    fun setCategories(datos: MutableList<NewDog>) {
        this.datos = datos
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val item: NewDog = datos[adapterPosition]
            Utils.logsUtils("Clicked -> $item")
            item.visibility = !item.visibility
            notifyItemChanged(adapterPosition)
        }
    }
}


