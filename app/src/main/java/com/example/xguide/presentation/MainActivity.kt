package com.example.xguide.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xguide.databinding.ActivityMainBinding
import com.example.xguide.presentation.adapter.NodeAdapter
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

    private val nodeAdapter = NodeAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        drawSplashScreen()
        setUpRecyclerView()
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

    private fun setUpRecyclerView() {

        val list = viewModel.tree.children
        nodeAdapter.submitList(list)
        val rcFolders: RecyclerView = binding.rcFolders
        rcFolders.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        with(rcFolders) {
            adapter = nodeAdapter
        }

        setUpOnClickListener()
        setUpOnLongClickListener()
    }

    private fun setUpOnClickListener() {
        nodeAdapter.setOnItemClickListener(object : NodeAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.nextLevel(position)
                viewModel.ldTree.observe(this@MainActivity) {
                    nodeAdapter.submitList(it)
                }
            }
        })
    }

    private fun setUpOnLongClickListener() {
        nodeAdapter.setOnItemLongClickListener(object : NodeAdapter.OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                Toast.makeText(this@MainActivity, "Delete item", Toast.LENGTH_SHORT).show()
            }
        })
    }
}