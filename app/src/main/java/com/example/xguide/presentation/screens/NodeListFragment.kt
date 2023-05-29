package com.example.xguide.presentation.screens

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.xguide.R
import com.example.xguide.databinding.FragmentNodeListBinding
import com.example.xguide.domain.Node
import com.example.xguide.presentation.adapter.NodeAdapter
import com.example.xguide.presentation.view_model.MainViewModel
import com.example.xguide.presentation.view_model.MyViewModelFactory


class NodeListFragment : Fragment() {

    private var _binding: FragmentNodeListBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentNodeInfoBinding is null")

    private val viewModelFactory by lazy {
        MyViewModelFactory(requireActivity().application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    private val nodeAdapter = NodeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNodeListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

        binding.btnAddNewNode.setOnClickListener {
            viewModel.addNode()
        }
    }

    override fun onDestroy() {
        if (_binding != null) {
            _binding = null
        }
        super.onDestroy()
    }

    private fun setUpRecyclerView() {

        val rcFolders: RecyclerView = binding.rcFolders
        rcFolders.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        with(rcFolders) {
            adapter = nodeAdapter
        }

        nodeAdapter.submitList(viewModel.nodes)

        setUpOnClickListener()
        setUpOnLongClickListener()

        observeViewModel(viewModel.ldChildrenTree) {
            nodeAdapter.submitList(it)
        }

        viewModel.ldParentTree.observe(viewLifecycleOwner) {
            nodeAdapter.submitList(it)
        }
    }

    private fun setUpOnClickListener() {
        nodeAdapter.setOnItemClickListener(object : NodeAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                viewModel.toChildren(position)
            }
        })
    }

    private fun observeViewModel(lv: LiveData<List<Node>>, action: (list: List<Node>) -> Unit) {
        lv.observe(viewLifecycleOwner) {
            if (it.size > 1) {
                action(it)
            } else {
                val node = it[0]
                openFragment(
                    NodeInfoFragment.newInstance(node.name),
                    NodeInfoFragment.FRAGMENT_NAME
                )
            }
        }
    }

    private fun openFragment(fragment: Fragment, fragmentName: String) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(fragmentName)
            .commit()
    }

    private fun setUpOnLongClickListener() {
        nodeAdapter.setOnItemLongClickListener(object : NodeAdapter.OnItemLongClickListener {
            override fun onItemLongClick(node: Node) {
                showAlertDialog(
                    getString(R.string.delete_item),
                    getString(R.string.delete_request),
                    getString(android.R.string.yes)
                ) {
                    viewModel.deleteNodeUseCase(node.name)
                    Toast.makeText(
                        context,
                        getString(R.string.item_has_deleted),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun showAlertDialog(
        dialogTitle: String, dialogMessage: String, buttonTitle: String, action: () -> Unit
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)

        builder.setNeutralButton(buttonTitle) { dialog, which ->
            action()
        }

        builder.setNeutralButton(android.R.string.no) { dialog, which ->
            Toast.makeText(
                context, getString(R.string.delete_canceled), Toast.LENGTH_SHORT
            ).show()
        }

        builder.show()
    }


    companion object {

        const val FRAGMENT_NAME = "NodeListFragment"

        @JvmStatic
        fun newInstance() =
            NodeListFragment().apply {

            }
    }
}