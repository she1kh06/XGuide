package com.example.xguide.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.xguide.databinding.FragmentNodeInfoBinding
import com.example.xguide.presentation.view_model.MainViewModel
import com.example.xguide.presentation.view_model.MyViewModelFactory

private const val ARG_NODE_NAME = "param1"

class NodeInfoFragment : Fragment() {

    private val viewModelFactory by lazy {
        MyViewModelFactory(requireActivity().application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private var nodeName: String? = null

    private var _binding: FragmentNodeInfoBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentNodeInfoBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nodeName = it.getString(ARG_NODE_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNodeInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textViewFolderName.text = nodeName
    }

    companion object {

        const val FRAGMENT_NAME = "NodeInfoFragment"

        @JvmStatic
        fun newInstance(nodeName: String) =
            NodeInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NODE_NAME, nodeName)
                }
            }
    }
}