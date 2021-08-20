package com.surveymonkey.ui.completedForms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.surveymonkey.databinding.FragmentCompletedFormsBinding
import com.surveymonkey.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CompletedFragment : BaseFragment() {
    private val safeArgs by navArgs<CompletedFragmentArgs>()
    override val viewModel: CompletedFormVM by viewModel{
        parametersOf(safeArgs.userId)
    }

    private val binding by lazy { FragmentCompletedFormsBinding.inflate(layoutInflater) }
    private val adapter = CompletedFormAdapter()

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
                CompletedFragmentDirections.actionSavedFragmentToSavedDetailFragment(it)
            )
        }
    }
}