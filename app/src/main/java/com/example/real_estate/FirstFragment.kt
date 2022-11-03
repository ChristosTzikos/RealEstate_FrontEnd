package com.example.real_estate

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil.bind
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.findNavController
import com.example.real_estate.databinding.ActivityMainBinding
import com.example.real_estate.databinding.ActivityMainBinding.bind
import com.example.real_estate.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

//import com.example.viewbinding.datababinding.FirstFragment
const val BASE_URL = "https://homeazy.herokuapp.com/"


class FirstFragment : Fragment(R.layout.fragment_first) {

    lateinit var autoTextView: AutoCompleteTextView  //accees xml
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val button = view.findViewById<Button>(R.id.btn_find)
        var radioGroup = view.findViewById<RadioGroup>(R.id.radio_Group)
        // autotextview to access the widget from the XML layout
        autoTextView = view.findViewById(R.id.searchView)
        //get the items of the string-array from the strings.xml file
        val languages = resources.getStringArray(R.array.countries_array)
        // Create adapter and add in AutoCompleteTextView
        val adapter1 = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1, languages
        )
        autoTextView.setAdapter(adapter1)


        button.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            binding = FragmentFirstBinding.bind(view)
            val city = binding

            val id: Int = radioGroup.checkedRadioButtonId
            if (id != -1) { // If any radio button checked from radio group
                val radio: RadioButton = view.findViewById<RadioButton>(id)
                Toast.makeText(activity, "On button click : ${radio.text}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(activity,"On button click : nothing selected",
                    Toast.LENGTH_SHORT).show()
            }
            getMyData()

            super.onViewCreated(view, savedInstanceState)

        }
        return view

    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<Test> {
//            override fun onResponse(
//                call: Call<List<Product>?>,
//                response: Response<List<Product>?>
//            ) {
//                val responseBody = response.body()!!
//
//                val resulthome = StringBuilder()
//                for (myData in responseBody){
//                    resulthome.append(myData.Name)
//                    resulthome.append("\n")
//
//                }
//                //val textView = findViewById<TextView>(R.id.txtId)
//                //textView.text = resulthome
//                Toast.makeText(activity, resulthome, Toast.LENGTH_SHORT).show()
//            }

//            override fun onFailure(call: Call<List<Product>?>, t: Throwable) {
//                Log.d("fragment_first", "onFailure: "+t.message)
//            }

            override fun onResponse(call: Call<Test>, response: Response<Test>) {
                val responseBody = response.body()?.products!!
                Timber.i("result: ${response.body()}")
                Timber.i("result: test")

                //val resulthome = StringBuilder()
                //for (myData in responseBody){
                //    resulthome.append(myData.region)
                //    resulthome.append("\n")

                //}
                //val textView = findViewById<TextView>(R.id.txtId)
                //textView.text = resulthome
                Toast.makeText(activity, response.body()!!.products[0].region.toString(), Toast.LENGTH_SHORT).show()

            }


            override fun onFailure(call: Call<Test>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


}





