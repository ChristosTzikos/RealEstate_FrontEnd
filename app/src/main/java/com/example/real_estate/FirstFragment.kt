package com.example.real_estate

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.real_estate.databinding.FragmentFirstBinding
import com.google.cloud.dialogflow.v2.Intent

//import com.example.viewbinding.datababinding.FirstFragment
const val BASE_URL = "https://homeazy.herokuapp.com/"


class FirstFragment : Fragment(R.layout.fragment_first), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var city = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)

        val adapter = ArrayAdapter.createFromResource(requireContext(), R.array.cities_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.searchView.adapter = adapter

        binding.searchView.onItemSelectedListener = this

        //set a default option checked, e.g. the buy option
        binding.buyChip.isChecked = true

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
            val buyOrRent = if(radioSelection == 0) "buy" else "rent"
            val rentMin = binding.minRentEdittext.text.toString()
            val rentMax = binding.maxRentEdittext.text.toString()

            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(buyOrRent, city, rentMin, rentMax))
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

