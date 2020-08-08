package com.example.isandroidtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import java.security.AccessController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Getting the Navigation Controller
        navController = Navigation.findNavController(this, R.id.fragment2)

        //Setting the navigation controller to Bottom Nav
        bottomNav.setupWithNavController(navController)

        //Setting up the action bar
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    //Setting up the back button
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

}