package com.surveymonkey.ui.completedForms.users

import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.surveymonkey.R
import com.surveymonkey.databinding.FragmentUsersBinding
import com.surveymonkey.manager.SessionManager
import com.surveymonkey.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class UsersFragment : BaseFragment() {
    override val viewModel: UserVM by viewModel()
    private val binding by lazy { FragmentUsersBinding.inflate(layoutInflater) }
    private val adapter = UsersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(SessionManager.isAdmin)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        init()
        initAdapter()
        initObservers()
        return binding.root
    }

    private fun init() {

    }

    private fun initObservers() {
        viewModel.userList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            if (SessionManager.isAdmin)
                activity?.finish()
        }
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = {
            findNavController().navigate(
                UsersFragmentDirections.actionUserFragmentToCompletedFragment(it.id.toLong())
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logoutItem) {
            SessionManager.loggedIn = false

            findNavController().navigate(
                UsersFragmentDirections.actionGlobalSplashFragment()
            )
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_admin, menu)
    }
}