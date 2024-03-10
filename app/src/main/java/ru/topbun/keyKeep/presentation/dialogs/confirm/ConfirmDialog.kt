package ru.topbun.keyKeep.presentation.dialogs.confirm

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.databinding.DialogConfirmBinding
import ru.topbun.keyKeep.presentation.base.BaseDialogFragment

@AndroidEntryPoint
class ConfirmDialog : BaseDialogFragment<DialogConfirmBinding>(DialogConfirmBinding::inflate){

    private val viewModel by viewModels<ConfirmViewModel>()

    override fun observeViewModel() {
        super.observeViewModel()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.state.collect{
                    when(it){
                        is ConfirmState.Result -> {
                            putResultInFragmentResult(it)
                            dismiss()
                        } else -> {}
                    }
                }
            }
        }
    }

    private fun putResultInFragmentResult(it: ConfirmState.Result) {
        setFragmentResult(
            CONFIRM_REQUEST_KEY,
            bundleOf(CONFIRM_EXTRA_KEY to it.result)
        )
    }

    override fun setListenersInView() {
        super.setListenersInView()
        binding.pinEditText.setOtpCompletionListener { value ->
            viewModel.checkCurrentPassword(value)
        }
    }

    companion object{
        const val CONFIRM_REQUEST_KEY = "confirm_request_key"
        const val CONFIRM_EXTRA_KEY = "confirm_extra_key"
    }

}