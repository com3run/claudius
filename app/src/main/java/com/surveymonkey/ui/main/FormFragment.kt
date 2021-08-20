package com.surveymonkey.ui.main

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.surveymonkey.R
import com.surveymonkey.data.model.ErrorDialogModel
import com.surveymonkey.databinding.FragmentFormBinding
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseFragment
import com.surveymonkey.ui.main.adapter.FormAdapter
import com.surveymonkey.utils.extensions.smoothScrollToPositionTop
import org.koin.androidx.viewmodel.ext.android.viewModel


class FormFragment : BaseFragment() {
    private val binding by lazy { FragmentFormBinding.inflate(layoutInflater) }
    override val viewModel: FormVM by viewModel()
    private val adapter = FormAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        initAdapter()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.baseFormList.observe(viewLifecycleOwner) {
            adapter.submitList(it) {
                binding.recyclerView.scrollToPosition(0)
            }
        }

        viewModel.errorMsg.observe(viewLifecycleOwner){
            showError(ErrorDialogModel(messageRes = it))
        }
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)

        adapter.onChange = {
            binding.recyclerView.smoothScrollToPositionTop(it + 1)
        }

        adapter.onSaveClick = {
            viewModel.saveForm()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.savedItem -> {
                findNavController().navigate(
                    if (SessionManager.isAdmin)
                        FormFragmentDirections.actionGlobalUserFragment()
                    else
                        FormFragmentDirections.actionFormFragmentToSavedFragment(SessionManager.userId)
                )
            }
            R.id.logoutItem -> {
                SessionManager.loggedIn = false

                findNavController().navigate(
                    FormFragmentDirections.actionGlobalSplashFragment()
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_form, menu)
    }
}