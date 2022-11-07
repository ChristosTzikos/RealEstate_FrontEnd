package com.example.real_estate

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


        Toast.makeText(requireContext(), "navigated to second fragment", Toast.LENGTH_SHORT).show()

        val buyOrRent = secondFragmentArgs.buyOrRent
        val city = secondFragmentArgs.city
        val minRent = secondFragmentArgs.minRent
        val maxRent = secondFragmentArgs.maxRent
        Log.d("data", "test params: $buyOrRent - $city - $minRent - $maxRent")



        getMyData(buyOrRent, city, minRent, maxRent)







        //recyclerView = binding.recyclerView
        recyclerView = view.findViewById(R.id.recycler_View)
        recyclerView.layoutManager = LinearLayoutManager(context)

        homeList = ArrayList()


        /*dummy
        homeList.add(Product("Large Apartment" , "Skg" , "240$" , "120sq.m." , R.drawable.apartment))
        homeList.add(Product("Hello" , "Hello" , "Hello" , "Hello" , R.drawable.apartment))
        homeList.add(Product("Hello" , "Hello" , "Hello" , "Hello" , R.drawable.apartment))
        homeList.add(Product("Hello" , "Hello" , "Hello" , "Hello" , R.drawable.apartment))
*/



        //recyclerAdapter = RecyclerAdapter(homeList)
        //recyclerView.adapter = recyclerAdapter



        binding.filters.setOnClickListener{
            findNavController().navigate(R.id.filterFragment)
        }




    }





    private fun getMyData(buyOrRent: String, city: String, minRent: String, maxRent: String) {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

//        val retrofitData = retrofitBuilder.getData(buyOrRent, city, minRent, maxRent)
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<Test> {

            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                response.body()!!.products?.forEach {
                    Log.d("data", "test data: ${it.area} - ${it.region} - ${it.name}- ${it.price}")
                    val responseBody = response.body()!!.products
                    recyclerAdapter = RecyclerAdapter(responseBody!!)
                    recyclerAdapter.notifyDataSetChanged()
                    recyclerView.adapter = recyclerAdapter
                }

            }

            override fun onFailure(call: Call<Test>, t: Throwable) {
                Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        })
    }

}





