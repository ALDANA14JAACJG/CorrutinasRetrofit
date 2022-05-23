package com.aldana.jorge.corrutinasretrofit

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.aldana.jorge.corrutinasretrofit.databinding.ItemJorgeBinding
import com.squareup.picasso.Picasso

class JorgeViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = ItemJorgeBinding.bind(view)

    fun bind(image:String){
        Picasso.get().load(image).into(binding.ivJorge)
    }
}