package com.example.real_estate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class SecondFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeList:ArrayList<Home>
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {


        


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_View)
        recyclerView.layoutManager = LinearLayoutManager(context)

        homeList = ArrayList()

        homeList.add(Home("Large Apartment" , "Skg" , "240$" , "120sq.m." , R.drawable.apartment))
        homeList.add(Home("Hello" , "Hello" , "Hello" , "Hello" , R.drawable.apartment))
        homeList.add(Home("Hello" , "Hello" , "Hello" , "Hello" , R.drawable.apartment))
        homeList.add(Home("Hello" , "Hello" , "Hello" , "Hello" , R.drawable.apartment))




        recyclerAdapter = RecyclerAdapter(homeList)
        recyclerView.adapter = recyclerAdapter
    }




}