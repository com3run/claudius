package com.surveymonkey.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.surveymonkey.databinding.FragmentLoginBinding
import com.surveymonkey.ui.base.BaseFragment
import com.surveymonkey.utils.extensions.handleProgress
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment() {
    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    override val viewModel: LoginVM by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        init()
        initObservers()
        return binding.root
    }

    private fun init() {
        binding.registerBtn.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

    private fun initObservers() {
        viewModel.loginUiState.observe(viewLifecycleOwner) {
            binding.registerBtn.handleProgress(it)
        }

        viewModel.navigateToForm.observe(viewLifecycleOwner) {
            findNavController().navigate(LoginFragmentDirections.actionGlobalFormFragment())
        }
    }
}