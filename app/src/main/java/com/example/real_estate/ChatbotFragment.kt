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


<<<<<<< HEAD:app/src/main/java/com/example/real_estate/ChatbotFragment.kt
/**
 * A simple [Fragment] subclass.
 * Use the [chatbot.newInstance] factory method to
 * create an instance of this fragment.
 */
class chatbot : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
=======
class DetailFragment : Fragment(R.layout.fragment_detail) {
>>>>>>> 97db980c857303e943c419f552b67406471a0cea:app/src/main/java/com/example/real_estate/DetailFragment.kt

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailFragmentArgs: DetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailBinding.bind(view)

        val btnBack = binding.btnBack
        btnBack.setOnClickListener(){
            findNavController().popBackStack()
        }


        //Get Data From Second Fragment
        //val id = detailFragmentArgs.id
        val name = detailFragmentArgs.name
        val area = detailFragmentArgs.area
        val price = detailFragmentArgs.price
        val region = detailFragmentArgs.region
        val photo = detailFragmentArgs.photo




        //Toast.makeText(requireContext(), "You clicked $name", Toast.LENGTH_SHORT).show()



        // Assign Values To Layout
        val detailTitle : TextView = binding.detailTitle
        val detailArea : TextView = binding.detailArea
        val detailPrice : TextView = binding.detailPrice
        val detailRegion : TextView = binding.detailRegion
        val detailPhoto : ImageView = binding.detailPhoto




        // Assign Data to Values
        detailTitle.text = name.toString()
        detailRegion.text = region.toString()
        detailArea.text = area.toString()
        detailPrice.text = price.toString()
        detailPhoto.load(photo)


    }

<<<<<<< HEAD:app/src/main/java/com/example/real_estate/ChatbotFragment.kt
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chatbot, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment chatbot.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            chatbot().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
=======
}



>>>>>>> 97db980c857303e943c419f552b67406471a0cea:app/src/main/java/com/example/real_estate/DetailFragment.kt
