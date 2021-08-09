package com.surveymonkey.ui.saved.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.surveymonkey.databinding.FragmentSavedDetailBinding
import com.surveymonkey.ui.base.BaseFragment
import com.surveymonkey.ui.main.adapter.FormAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class SavedDetailFragment : BaseFragment() {
    private val binding by lazy { FragmentSavedDetailBinding.inflate(layoutInflater) }
    private val safeArgs: SavedDetailFragmentArgs by navArgs()

    override val viewModel: SavedDetailVM by viewModel {
        parametersOf(safeArgs.pojo)
    }

    private val adapter = FormAdapter(false)

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
        viewModel.baseFormList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = adapter
    }
}