package com.aldana.jorge.corrutinasretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class JorgeAdapter(private val images:List<String>):RecyclerView.Adapter<JorgeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JorgeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return JorgeViewHolder(layoutInflater.inflate(R.layout.item_jorge,parent,false))
    }

    override fun onBindViewHolder(holder: JorgeViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = images.size
}