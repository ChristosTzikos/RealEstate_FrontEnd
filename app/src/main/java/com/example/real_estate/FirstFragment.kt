package com.example.real_estate

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.real_estate.databinding.FragmentFirstBinding
import com.google.cloud.dialogflow.v2.Intent
import timber.log.Timber

//import com.example.viewbinding.datababinding.FirstFragment
const val BASE_URL = "https://homezy1.herokuapp.com/"


class FirstFragment : Fragment(R.layout.fragment_first), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var city = ""

    private var maxArea: String = "500"
    private var minArea: String = "1"


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)

        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.cities_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.searchView.adapter = adapter

        binding.searchView.onItemSelectedListener = this

        //set a default option checked, e.g. the buy option
        binding.buyChip.isChecked = true

        //navigate from chatbot_activity to second fragment with extras URL
        var extrasurl = (requireActivity() as MainActivity).intent.extras?.getString("url")
        if (extrasurl != null){
            val responceSTR: String = extrasurl
            val strarray: List<String> = responceSTR.split("&")
            Log.d("this is a test", strarray.toString())
            val thodoris = strarray[2]
            Log.d("this is an array test", thodoris)
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment("", "", "", "", "", "", strarray[0], strarray[1], strarray[2], strarray[3]))
        }

        //fragment to chatbot activity navigation
        binding.btnChat.setOnClickListener {
            val intent = android.content.Intent(
                this@FirstFragment.requireContext(),
                Chatbot_activity::class.java
            )
            startActivity(intent)
        }

        binding.btnFind.setOnClickListener {

            //before navigating we need to get the user's inputs
            val radioSelection = binding.radioGroup.checkedRadioButtonId
            val buyOrRent = if(binding.radioGroup.getCheckedRadioButtonId() != -1 && binding.rentChip.isChecked ){
                "rent"
            } else{
                "buy"
            }
            var minRent = binding.minRentEdittext.text.toString()
            var maxRent = binding.maxRentEdittext.text.toString()

            if (minRent == ""){
                minRent = "0"
            }
            if (maxRent == ""){
                maxRent = "100000000"
            }



            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(buyOrRent, city, minRent, maxRent, maxArea,minArea, "","","",""))
        }

        binding.btnLoginFragment.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_loginFragment)
        }


    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        val selection = p0?.getItemAtPosition(p2).toString()
        city = selection
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //do nothing
    }


}

