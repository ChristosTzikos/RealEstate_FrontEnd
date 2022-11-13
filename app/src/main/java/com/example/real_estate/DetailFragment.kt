package com.example.real_estate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.real_estate.databinding.FragmentDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailFragmentArgs: DetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailBinding.bind(view)




        //Get Data From Second Fragment
        //val id = detailFragmentArgs.id
        val name = detailFragmentArgs.name
        val area = detailFragmentArgs.area
        val price = detailFragmentArgs.price
        val region = detailFragmentArgs.region
        val photo = detailFragmentArgs.photo
        val description = detailFragmentArgs.description




        //Toast.makeText(requireContext(), "You clicked $name", Toast.LENGTH_SHORT).show()



        // Assign Values To Layout
        val detailTitle : TextView = binding.detailTitle
        val detailArea : TextView = binding.detailArea
        val detailPrice : TextView = binding.detailPrice
        val detailRegion : TextView = binding.detailRegion
        val detailPhoto : ImageView = binding.detailPhoto
        val detailDesc : TextView = binding.detailDesc




        // Assign Data to Values
        detailTitle.text = name.toString()
        detailRegion.text = region.toString()
        detailArea.text = area.toString()
        detailPrice.text = price.toString()
        detailPhoto.load(photo)
        detailDesc.text = description.toString()


    }

}