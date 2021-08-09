package com.surveymonkey.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.surveymonkey.databinding.FragmentSplashBinding
import com.surveymonkey.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : BaseFragment() {
    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }
    override val viewModel: SplashVM by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.isUserLoggedIn
            .observe(viewLifecycleOwner) {
                val navArgs = if (it) {
                    SplashFragmentDirections.actionGlobalFormFragment()
                } else
                    SplashFragmentDirections.actionSplashFragmentToLoginFragment()

                findNavController().navigate(navArgs)
            }

        return binding.root
    }

    override fun hideActionBar() = true
}