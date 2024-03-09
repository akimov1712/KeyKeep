package ru.topbun.keyKeep.presentation.screens.search

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.databinding.FragmentSearchBinding
import ru.topbun.keyKeep.presentation.base.BaseFragment
import ru.topbun.keyKeep.presentation.base.adapter.PasswordAdapter
import ru.topbun.keyKeep.presentation.screens.home.HomeState

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private val viewModel by viewModels<SearchViewModel>()
    private val adapter by lazy { PasswordAdapter() }

    override fun setAdapters() {
        super.setAdapters()
        binding.rvPasswords.adapter = adapter
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
            etSearch.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    viewModel.getPassword(s.toString())
                }
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