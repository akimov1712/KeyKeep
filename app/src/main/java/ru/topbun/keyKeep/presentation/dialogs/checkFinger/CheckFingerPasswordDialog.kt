package ru.topbun.keyKeep.presentation.dialogs.checkFinger

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.databinding.DialogCheckFingerPasswordBinding
import ru.topbun.keyKeep.domain.enities.FingerResponseEntity
import ru.topbun.keyKeep.domain.enities.FingerStateEnum.*
import ru.topbun.keyKeep.presentation.base.BaseDialogFragment
import ru.topbun.keyKeep.presentation.base.CustomToast
import ru.topbun.keyKeep.utils.vibratePhone

@AndroidEntryPoint
class CheckFingerPasswordDialog :
    BaseDialogFragment<DialogCheckFingerPasswordBinding>(DialogCheckFingerPasswordBinding::inflate) {

    private val viewModel by viewModels<CheckFingerViewModel>()

    override fun observeViewModel() {
        super.observeViewModel()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {
                    when (it) {
                        is CheckFingerState.Result -> {
                            defineAction(it.result)
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    private fun defineAction(result: FingerResponseEntity) {
        when (result.state) {
            NOT_SUPPORT -> {
                showToast(result.message)
            }

            NO_REGISTERED -> {
                showToast(result.message)
            }

            AUTH_ERROR -> {
                CustomToast.cancelToast()
                showToast(result.message)
            }

            AUTH_SUCCESS -> {
                CustomToast.cancelToast()
                showToast(result.message)
                dismiss()
            }

            AUTH_FAILED -> {
                CustomToast.cancelToast()
                showToast(result.message)
                requireContext().vibratePhone()
            }
        }
    }

    private fun showToast(message: String) {
        CustomToast.toastDefault(requireContext(), message)
    }


}