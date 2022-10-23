package com.example.real_estate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView



class SecondFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null


    override fun onCreateView(


        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)


        recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        recyclerView.adapter = adapter









        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)


    }




}