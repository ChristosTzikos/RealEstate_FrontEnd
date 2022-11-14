package com.example.real_estate

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.graphics.Paint.Align
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.real_estate.databinding.FragmentSecondBinding
import kotlinx.android.synthetic.main.fragment_second.view.*
import java.text.ParsePosition


class SecondFragment : Fragment(R.layout.fragment_second) {

    private  var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private val secondFragmentArgs: SecondFragmentArgs by navArgs()

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeList:ArrayList<Product>
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onViewCreated(view:View , savedInstanceState:Bundle?){

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSecondBinding.bind(view)


        Toast.makeText(requireContext(), "Loading..", Toast.LENGTH_SHORT).show()

        val buyOrRent = secondFragmentArgs.buyOrRent
        val city = secondFragmentArgs.city
        val minRent = secondFragmentArgs.minRent
        val maxRent = secondFragmentArgs.maxRent
        //val roof = secondFragmentArgs.roof
        val minArea = secondFragmentArgs.minArea
        val maxArea = secondFragmentArgs.maxArea



        Log.d("data", "test params: $buyOrRent - $city - $minRent - $maxRent")

        val buyOrRent2 = secondFragmentArgs.buyOrrent2
        val city2 = secondFragmentArgs.city2
        val minRent2 = secondFragmentArgs.minRent2
        val maxRent2 = secondFragmentArgs.maxRent2
        if (buyOrRent2 != ""){
            Log.e("this is a chat demo 2", city2 + " " + minRent2 + " " + maxRent2 + " " + buyOrRent2)
            getMyData(buyOrRent2, city2, minRent2.toInt(), maxRent2.toInt(), "1".toInt(), "5000".toInt())
        }else {
            Log.e("this is a chat demo", city + " " + minRent + " " + maxRent + " " + buyOrRent + " " + minArea + "" + maxArea)
            getMyData(buyOrRent, city, minRent.toInt(), maxRent.toInt(),minArea.toInt(),maxArea.toInt())


        }

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        homeList = ArrayList()
        recyclerView.setHasFixedSize(true)


        /*dummy
        homeList.add(Product("Large Apartment" , "Skg" , "240$" , "120sq.m." , R.drawable.apartment))
        homeList.add(Product("Hello" , "Hello" , "Hello" , "Hello" , R.drawable.apartment))
        homeList.add(Product("Hello" , "Hello" , "Hello" , "Hello" , R.drawable.apartment))
        homeList.add(Product("Hello" , "Hello" , "Hello" , "Hello" , R.drawable.apartment))
*/



        //recyclerAdapter = RecyclerAdapter(homeList)
        //recyclerView.adapter = recyclerAdapter



        binding.filters.setOnClickListener{
           findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToFilterFragment(city))
            //findNavController().navigate(R.id.filterFragment)
        }




    }





    private fun getMyData(buyOrRent: String, city: String, minRent: Int, maxRent: Int, minArea: Int, maxArea: Int) {

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

//        val retrofitData = retrofitBuilder.getData(buyOrRent, city, minRent, maxRent)
        Log.e("dimitris", city + " " + minRent + " " + maxRent + " " + buyOrRent + " " + minArea + " " + maxArea)
        val retrofitData = retrofitBuilder.getData(city,buyOrRent,minRent,maxRent,minArea,maxArea)

        retrofitData.enqueue(object : Callback<Test> {

            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                binding.progressbar.visibility = View.GONE

                response.body()!!.products?.forEach {
                    Log.d("data", "test data: ${it.area} - ${it.region} - ${it.name}- ${it.price} - ${it.description} ")
                    val responseBody = response.body()!!.products
                    recyclerAdapter = RecyclerAdapter(responseBody!!)
                    recyclerAdapter.notifyDataSetChanged()
                    recyclerView.adapter = recyclerAdapter
                    recyclerAdapter.setOnItemClickListener(object :
                        RecyclerAdapter.onItemClickListener {


                        override fun onItemClick(position: Int) {



                            var that = responseBody!![position]
                            findNavController().navigate(SecondFragmentDirections.actionSecondFragmentToDetailFragment(
                                // Give ONLY Strings to Detail Fragment (Non-Null Errors)

                                that.name.toString() ,
                                that.id.toString() ,
                                that.region.toString(),
                                that.area.toString() ,
                                that.price.toString() ,
                                that.photo.toString(),
                                that.description.toString()
                            ))
                        }


                    })

                }
            }

            override fun onFailure(call: Call<Test>, t: Throwable) {
                Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        })
    }}











