package com.example.real_estate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.real_estate.databinding.FragmentFilterBinding



class FilterFragment : Fragment(R.layout.fragment_filter) {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFilterBinding.bind(view)


        binding.btnBack.setOnClickListener{


            findNavController().popBackStack()

        }

        binding.btnFindLast.setOnClickListener{
            /* val filter etc
            ..
            ..
            ..


            findNavController().navigate(FilterFragmentDirections.actionFilterFragmentToSecondFragment())*/
        }





    }
}

