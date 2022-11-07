package com.example.real_estate


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class RecyclerAdapter(private val homeList: List<Product2>)
    : RecyclerView.Adapter<RecyclerAdapter.HomeViewHolder>() {


    class HomeViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.card_image)
        val Name: TextView = itemView.findViewById(R.id.card_title)
        val area: TextView = itemView.findViewById(R.id.sqr_ft)
        val price: TextView = itemView.findViewById(R.id.card_price)
        val region: TextView = itemView.findViewById(R.id.card_location)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout , parent , false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val home = homeList!![position]
        //holder.photo = home.Photo
        holder.Name.text =  home.name
        //holder.area.text = home.area.toString()
        //holder.price.text = home.price.toString()
        //holder.region.text = home.region
    }

    override fun getItemCount(): Int {
        return homeList.size
    }
}




