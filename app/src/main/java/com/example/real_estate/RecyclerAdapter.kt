package com.example.real_estate


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load


class RecyclerAdapter(private val homeList: List<Product2>)
    : RecyclerView.Adapter<RecyclerAdapter.HomeViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }


    @Suppress("DEPRECATION")
    class HomeViewHolder(itemView:View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.card_image)
        val name: TextView = itemView.findViewById(R.id.card_title)
        val area: TextView = itemView.findViewById(R.id.card_area)
        val price: TextView = itemView.findViewById(R.id.card_price)
        val region: TextView = itemView.findViewById(R.id.card_location)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout , parent , false)
        return HomeViewHolder( itemView , mListener)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val home = homeList!![position]
        holder.photo.load(home.photo)
        holder.name.text =  home.name
        holder.area.text = home.area.toString()
        holder.price.text = home.price.toString()
        holder.region.text = home.region


    }

    override fun getItemCount(): Int {
        return homeList.size
    }
}




