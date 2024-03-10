package ru.topbun.keyKeep.presentation.dialogs.confirmDelete

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.R
import ru.topbun.keyKeep.databinding.DialogConfirmBinding
import ru.topbun.keyKeep.databinding.DialogConfirmDeleteBinding
import ru.topbun.keyKeep.presentation.base.BaseDialogFragment


@AndroidEntryPoint
class ConfirmDeleteDialog : BaseDialogFragment<DialogConfirmDeleteBinding>(DialogConfirmDeleteBinding::inflate) {

    private val args by navArgs<ConfirmDeleteDialogArgs>()
    private val viewModel by viewModels<ConfirmDeleteViewModel>()

    override fun setViews() {
        super.setViews()
        setDescr()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        lifecycleScope.launch {
            viewModel.shouldCloseScreen.collect{
                findNavController().popBackStack()
            }
        }
    }

    private fun setDescr() {
        val descr = requireContext().getString(R.string.you_really_delete_this_password)
        binding.tvDescr.text = "$descr ${args.name}"
    }

    override fun setListenersInView() {
        super.setListenersInView()
        with(binding){
            btnDelete.setOnClickListener {
                viewModel.deletePassword(args.passwordId)
            }
            btnCancel.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }
}