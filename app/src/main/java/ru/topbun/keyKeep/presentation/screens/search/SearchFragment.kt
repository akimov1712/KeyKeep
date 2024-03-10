package ru.topbun.keyKeep.presentation.screens.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.databinding.FragmentSearchBinding
import ru.topbun.keyKeep.domain.enities.ConfirmTypeEnum
import ru.topbun.keyKeep.presentation.base.BaseFragment
import ru.topbun.keyKeep.presentation.base.CustomToast
import ru.topbun.keyKeep.presentation.base.adapter.PasswordAdapter
import ru.topbun.keyKeep.presentation.dialogs.confirm.ConfirmDialog
import ru.topbun.keyKeep.presentation.screens.home.HomeFragmentDirections
import ru.topbun.keyKeep.presentation.screens.home.HomeState

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by viewModels<SearchViewModel>()
    private val adapter by lazy { PasswordAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getValueFromFragmentResult()
    }

    override fun setAdapters() {
        super.setAdapters()
        binding.rvPasswords.adapter = adapter
        adapter.setOnItemClickListener = {
            setFragmentResult(
                ConfirmDialog.EXTERNAL_REQUEST_KEY,
                bundleOf(
                    ConfirmDialog.EXTERNAL_KEY_EXTRA to it,
                )
            )
            findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToConfirmDialog(
                ConfirmTypeEnum.SHOW_PASSWORD
            ))
        }
    }

    private fun getValueFromFragmentResult() {
        setFragmentResultListener(ConfirmDialog.CONFIRM_REQUEST_KEY) { _, bundle ->
            val isValid = bundle.getBoolean(ConfirmDialog.CONFIRM_EXTRA_KEY, false)
            val type =
                bundle.getSerializable(ConfirmDialog.CONFIRM_TYPE_EXTRA_KEY) as? ConfirmTypeEnum
            if (isValid) {
                when (type) {
                    ConfirmTypeEnum.SHOW_PASSWORD -> {
                        val id = bundle.getInt(ConfirmDialog.CONFIRM_PASSWORD_ID_EXTRA_KEY)
                        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToAddOrEditFragment(id))
                    }
                    else -> {}
                }
            } else {
                CustomToast.toastDefault(requireContext(), "Неправильный пароль")
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding){
            with(viewModel){
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.RESUMED){
                        state.collect{
                            hideElements()
                            when(it){
                                is SearchState.Loading -> {
                                    progressBar.visibility = View.VISIBLE
                                }
                                is SearchState.PasswordList -> {
                                    rvPasswords.visibility = View.VISIBLE
                                    adapter.submitList(it.list)
                                }
                                is SearchState.EmptyList -> {
                                    tvEmptyList.visibility = View.VISIBLE
                                } else -> {}
                            }
                        }
                    }
                }
            }
        }
    }

    override fun setListenersInView() {
        super.setListenersInView()
        with(binding){
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            etSearch.addTextChangedListener(afterTextChanged = {
                viewModel.getPassword(it.toString())
            })
        }
    }

    private fun hideElements(){
        with(binding){
            progressBar.visibility = View.GONE
            rvPasswords.visibility = View.GONE
            tvEmptyList.visibility = View.GONE
        }
    }

}