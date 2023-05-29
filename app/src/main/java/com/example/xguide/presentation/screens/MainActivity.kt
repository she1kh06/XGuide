package com.example.xguide.presentation.screens

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.xguide.R
import com.example.xguide.databinding.ActivityMainBinding
import com.example.xguide.presentation.node_navigation.Navigation
import com.example.xguide.presentation.view_model.MainViewModel
import com.example.xguide.presentation.view_model.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModelFactory by lazy {
        MyViewModelFactory(application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        drawSplashScreen()
        openNodeListFragment()
    }

    private fun drawSplashScreen() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    return true
                }
            }
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.toParent()
    }

    private fun openNodeListFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_container, NodeListFragment.newInstance())
            .addToBackStack(NodeListFragment.FRAGMENT_NAME)
            .commit()
    }
}