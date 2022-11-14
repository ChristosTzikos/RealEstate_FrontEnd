package com.example.real_estate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {

    private  lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

//    override fun onNewIntent(intent: Intent?) {
//        super.onNewIntent(intent)
//        val extras = intent!!.extras
//        if (extras != null){
//            if (extras["url"] != null){
//                Log.d("asd", "blaBLABLALB")
//                val navHostdemo = supportFragmentManager
//                    .findFragmentById(R.id.secondFragment) as NavHostFragment
//                navController = navHostdemo.navController
//
//                val inflater = navController.navInflater
//                val graph = inflater.inflate(R.navigation.test_nav)
//
//                graph.setStartDestination(R.id.secondFragment)
//                navController.graph = graph
//            }
//        }
//    }
}
