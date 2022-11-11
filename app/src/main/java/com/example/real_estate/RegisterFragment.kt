package com.example.real_estate

import android.os.Bundle
import android.provider.ContactsContract
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
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var cnfPassword: EditText

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
        var view = inflater.inflate(R.layout.fragment_register, container, false)

        username = view.findViewById(R.id.reg_username)
        password = view.findViewById(R.id.reg_password)
        cnfPassword = view.findViewById(R.id.reg_cnf_password)

        //fAuth = Firebase.auth

        view.findViewById<Button>(R.id.btn_login_reg).setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            /*var navRegister = activity as FragmentNavigation
            navRegister.navigateFrag(LoginFragment(),false)*/
        }

        view.findViewById<Button>(R.id.btn_register_reg).setOnClickListener {
            validateEmptyForm()
        }
        return view

    }
    private fun signup( email: String, password: String) {
        Log.e(email, "kostas")

        RetrofitClient.instance.createUser(email, password)
            .enqueue(object: Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(context, "Internal Server Error", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    Toast.makeText(context, response.body()?.message + "with email: " + email, Toast.LENGTH_LONG).show()

                }

            })
//
//        val retIn = RetrofitInstance.getRetrofitInstance().create(APIInterface::class.java)
//        val registerInfo = UserBody(email,password)
//
//        retIn.registerUser(registerInfo).enqueue(object :
//            Callback<ResponseBody> {
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//
//            }
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                if (response.code() == 201) {
//                    Toast.makeText(context,"Register Successful",Toast.LENGTH_SHORT).show()
//                } else{
//                    Toast.makeText(context,"Register not Successful",Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
    }

    private fun validateEmptyForm(){
        val icon = AppCompatResources.getDrawable(requireContext(),
            R.drawable.ic_warning)

        icon?.setBounds(0,0,icon.intrinsicWidth,icon.intrinsicHeight)
        when{
            TextUtils.isEmpty(username.text.toString().trim())->{
                username.setError("Please Enter Username",icon)
            }
            TextUtils.isEmpty(password.text.toString().trim())->{
                password.setError("Please Enter Password",icon)
            }
            TextUtils.isEmpty(cnfPassword.text.toString().trim())->{
                cnfPassword.setError("Please Enter Password Again",icon)
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty() &&
                    cnfPassword.text.toString().isNotEmpty() ->
            {

                if (username.text.toString().matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){
                    if(password.text.toString().length>=5){

                        if(password.text.toString() == cnfPassword.text.toString()){
                            signup(username.text.toString().trim(), password.text.toString().trim())
                            //firebaseSignUp()
                            //Toast.makeText(context,"88 Register Successful",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            cnfPassword.setError("Password din't match",icon)
                        }
                    }
                    else{
                        password.setError("Please Enter at least 5 character",icon)
                    }

                }else{
                    username.setError("Please Enter Valid Email Id",icon)
                }
            }

        }
    }


}