package com.example.real_estate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text


class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    // edw logika tha pairnoyme apo back end

    private var titles = arrayOf("Large Apartment" ,"Large Apartment" ,"Large Apartment" )

    private var details = arrayOf("Panorama Thessaloniki","Panorama Thessaloniki","Panorama Thessaloniki")

    private var prices = arrayOf("900$" ,"900$","900$")

    private var squares = arrayOf("120 sq.m.", "120 sq.m." , "120 sq.m.")

    private val images = intArrayOf(R.drawable.apartment , R.drawable.apartment , R.drawable.apartment  )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return titles.size

    }


    override fun onBindViewHolder(holder:RecyclerAdapter.ViewHolder , position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemPrice.text = prices[position]
        holder.itemSquare.text = squares[position]
        holder.itemImage.setImageResource(images[position])
    }

    inner class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail:TextView // topothesia
        var itemPrice: TextView
        var itemSquare: TextView

        init {
            itemImage = itemView.findViewById(R.id.card_image)
            itemTitle = itemView.findViewById(R.id.card_title)
            itemDetail = itemView.findViewById(R.id.card_location)
            itemPrice = itemView.findViewById(R.id.card_price)
            itemSquare = itemView.findViewById(R.id.sqr_ft)
            }

    }

}


