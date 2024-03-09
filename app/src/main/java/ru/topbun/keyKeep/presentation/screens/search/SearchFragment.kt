package ru.topbun.keyKeep.presentation.screens.search

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.topbun.keyKeep.databinding.FragmentSearchBinding
import ru.topbun.keyKeep.presentation.base.BaseFragment

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding){
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}