package com.surveymonkey.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.surveymonkey.utils.dialogs.ErrorDialog
import com.surveymonkey.utils.extensions.supportActionBar

abstract class BaseFragment : Fragment() {
    private val _errorDialog = ErrorDialog(lifecycle)

    private val errorDialog by lazy {
        return@lazy _errorDialog.createDialog(
            requireContext(),
            layoutInflater
        )
    }

    protected open val viewModel: BaseViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel?.showErrorDialog
            ?.observe(viewLifecycleOwner) { errorDialog.show(context, it) }

        initActionBar()
    }

    private fun initActionBar() {
        if (parentFragment is NavHostFragment) {
            if (hideActionBar())
                supportActionBar()?.hide()
            else
                supportActionBar()?.show()
        }
    }

    open fun hideActionBar(): Boolean {
        return false
    }
}