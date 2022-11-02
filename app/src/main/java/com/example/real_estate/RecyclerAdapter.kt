package com.example.real_estate


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class RecyclerAdapter(private val homeList:ArrayList<Home>)
    : RecyclerView.Adapter<RecyclerAdapter.HomeViewHolder>() {


    class HomeViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.card_image)
        val title: TextView = itemView.findViewById(R.id.card_title)
        val detail: TextView = itemView.findViewById(R.id.sqr_ft)
        val price: TextView = itemView.findViewById(R.id.card_price)
        val location: TextView = itemView.findViewById(R.id.card_location)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout , parent , false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val home = homeList[position]
        holder.image.setImageResource(home.images)
        holder.title.text = home.titles
        holder.detail.text = home.squares
        holder.price.text = home.prices
        holder.location.text = home.details
    }

    override fun getItemCount(): Int {
        return homeList.size
    }
}




