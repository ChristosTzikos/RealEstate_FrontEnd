package com.example.real_estate

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_login, container, false)

        username = view.findViewById(R.id.log_username)
        password = view.findViewById(R.id.log_password)


        view.findViewById<Button>(R.id.btn_register).setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            /*var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(RegisterFragment(),false)*/
        }

        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            validateForm()
        }

        return view
    }

    private fun login( email: String, password: String) {
        Log.e( password, " kala")

        RetrofitClient.instance.loginUser(email, password)
            .enqueue(object : Callback<UserLogin> {
                override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                    Toast.makeText(context, "Wrong Email or Password", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<UserLogin>, response: Response<UserLogin>) {
                    Toast.makeText(context, response.body()?.message, Toast.LENGTH_LONG).show()

                }

            })
    }




    private fun validateForm(){
        val icon = AppCompatResources.getDrawable(requireContext(),
            R.drawable.ic_warning)

        icon?.setBounds(0,0,icon.intrinsicWidth,icon.intrinsicHeight)
        when {
            TextUtils.isEmpty(username.text.toString().trim()) -> {
                username.setError("Please Enter Username", icon)
            }
            TextUtils.isEmpty(password.text.toString().trim()) -> {
                password.setError("Please Enter Password", icon)
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() -> {

                if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))) {
                    login(username.text.toString().trim(), password.text.toString().trim())
                    //firebaseSignIn()
                } else {
                    username.setError("Please Enter Valid Email Id", icon)
                }
            }
        }
    }

}