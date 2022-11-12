package com.example.real_estate

import android.os.Bundle
import android.util.Log
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

    private var maxArea: String = "0"
    private var minArea: String = "0"
    private var minRent: String = "0"
    private var maxRent: String = "0"
    private var buyOrRent: String = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFilterBinding.bind(view)


        binding.btnBack.setOnClickListener{


            findNavController().popBackStack()

        }

        binding.btnFindLast.setOnClickListener{

            val radioSelection = binding.radioGroup.checkedRadioButtonId
            val buyOrRent = if(binding.radioGroup.getCheckedRadioButtonId() != -1 && binding.rentChip.isChecked ){
                "rent"
            } else{
                "buy"
            }

            var minArea  = binding.minArea.text.toString()
            var maxArea = binding.maxArea.text.toString()

            var minRent = binding.minRentEditext.text.toString()
            var maxRent = binding.maxPriceLayout.text.toString()

            Log.e("kostas",buyOrRent+minArea+maxArea+minRent+maxRent)

//            if (minRent == ""){
//                minRent = "0"
//            }
//            if (maxRent == ""){
//                maxRent = "100000000"
//            }
//
//            if (minArea == ""){
//                minArea = "0"
//            }
//            if (maxArea == ""){
//                maxArea = "100000000"
//            }


            findNavController().navigate(FilterFragmentDirections.actionFilterFragmentToSecondFragment(buyOrRent,"",minRent,maxRent,minArea,maxArea))
        }





    }
}

