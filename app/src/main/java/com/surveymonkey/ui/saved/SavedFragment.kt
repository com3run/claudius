package com.surveymonkey.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.surveymonkey.databinding.FragmentSavedBinding
import com.surveymonkey.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SavedFragment : BaseFragment() {
    private val binding by lazy { FragmentSavedBinding.inflate(layoutInflater) }
    override val viewModel: SavedVM by viewModel()
    private val adapter = SavedFormAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        initAdapter()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.formList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = {
            findNavController().navigate(
                SavedFragmentDirections.actionSavedFragmentToSavedDetailFragment(it)
            )
        }
    }
}