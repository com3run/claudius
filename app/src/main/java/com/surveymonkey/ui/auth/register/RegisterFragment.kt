package com.surveymonkey.ui.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.surveymonkey.databinding.FragmentRegisterBinding
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.auth.login.LoginFragmentDirections
import com.surveymonkey.ui.base.BaseFragment
import com.surveymonkey.utils.extensions.handleProgress
import com.surveymonkey.utils.showSoftKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : BaseFragment() {
    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    override val viewModel: RegisterVM by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        binding.nameTil.showSoftKeyboard()

        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.registerUiState.observe(viewLifecycleOwner) {
            binding.registerBtn.handleProgress(it)
        }

        viewModel.navigateToNext.observe(viewLifecycleOwner) {
            findNavController().navigate(
                if (SessionManager.isAdmin)
                    RegisterFragmentDirections.actionGlobalUserFragment()
                else
                    RegisterFragmentDirections.actionGlobalFormFragment()
            )
        }
    }
}