package ru.topbun.keyKeep.presentation.screens.home

import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.databinding.FragmentHomeBinding
import ru.topbun.keyKeep.presentation.base.BaseFragment
import ru.topbun.keyKeep.presentation.base.adapter.PasswordAdapter
import ru.topbun.keyKeep.presentation.dialogs.confirm.ConfirmDialog

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy { PasswordAdapter() }

    override fun setAdapters() {
        super.setAdapters()
        binding.rvPasswords.adapter = adapter
        setFragmentResultListener(ConfirmDialog.CONFIRM_REQUEST_KEY) { _, bundle ->
            val value = bundle.getBoolean(ConfirmDialog.CONFIRM_EXTRA_KEY, false)
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding) {
            with(viewModel) {
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.RESUMED) {
                        state.collect {
                            hideElements()
                            when (it) {
                                is HomeState.Loading -> {
                                    progressBar.visibility = View.VISIBLE
                                }

                                is HomeState.PasswordList -> {
                                    rvPasswords.visibility = View.VISIBLE
                                    adapter.submitList(it.list)
                                }

                                is HomeState.EmptyList -> {
                                    tvEmptyList.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun setListenersInView() {
        super.setListenersInView()
        with(binding) {
            tvTitle.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToConfirmDialog())
            }
            btnMenu.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            btnSearch.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            }
            btnSetMasterPassword.setOnClickListener {
                closeDrawerLayout()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSetMasterPasswordDialog())
            }
            btnSetFingerPassword.setOnClickListener {
                closeDrawerLayout()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSetFingerPasswordDialog())
            }
            btnAddPassword.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddOrEditFragment())
            }
        }
    }

    private fun closeDrawerLayout() {
        binding.drawerLayout.closeDrawers()
    }

    private fun hideElements() {
        with(binding) {
            progressBar.visibility = View.GONE
            rvPasswords.visibility = View.GONE
            tvEmptyList.visibility = View.GONE
        }
    }
}