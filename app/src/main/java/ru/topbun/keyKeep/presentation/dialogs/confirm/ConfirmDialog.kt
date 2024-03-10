package ru.topbun.keyKeep.presentation.dialogs.confirm

import android.os.Bundle
import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.topbun.Const
import ru.topbun.keyKeep.databinding.DialogConfirmBinding
import ru.topbun.keyKeep.domain.enities.ConfirmTypeEnum
import ru.topbun.keyKeep.domain.enities.FingerResponseEntity
import ru.topbun.keyKeep.domain.enities.FingerStateEnum
import ru.topbun.keyKeep.presentation.base.BaseDialogFragment
import ru.topbun.keyKeep.presentation.base.CustomToast
import ru.topbun.keyKeep.presentation.dialogs.checkFinger.CheckFingerState
import ru.topbun.keyKeep.presentation.screens.home.HomeFragmentDirections
import ru.topbun.keyKeep.utils.vibratePhone

@AndroidEntryPoint
class ConfirmDialog : BaseDialogFragment<DialogConfirmBinding>(DialogConfirmBinding::inflate){

    private val args by navArgs<ConfirmDialogArgs>()
    private val viewModel by viewModels<ConfirmViewModel>()
    private var passwordId = Const.UNDEFINED_ID

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getValueFromFragmentResult()
    }

    private fun getValueFromFragmentResult(){
        setFragmentResultListener(EXTERNAL_REQUEST_KEY) { _, bundle ->
            passwordId = bundle.getInt(EXTERNAL_KEY_EXTRA)
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.state.collect{
                    when(it){
                        is ConfirmState.MasterPasswordResult -> {
                            verificationPassed(it.result)
                        }
                        is ConfirmState.FingerResult -> {
                            defineAction(it.result)
                        }else -> {}
                    }
                }
            }
        }
    }

    private fun verificationPassed(value:Boolean) {
        findNavController().navigateUp()
        putResultInFragmentResult(value, args.type, passwordId)
    }

    private fun putResultInFragmentResult(result: Boolean, type: ConfirmTypeEnum, passwordId: Int) {
        setFragmentResult(
            CONFIRM_REQUEST_KEY,
            bundleOf(
                CONFIRM_EXTRA_KEY to result,
                CONFIRM_TYPE_EXTRA_KEY to type,
                CONFIRM_PASSWORD_ID_EXTRA_KEY to passwordId,
            )
        )
    }

    override fun setListenersInView() {
        super.setListenersInView()
        binding.pinEditText.setOtpCompletionListener { value ->
            viewModel.checkCurrentPassword(value)
        }
    }

    private fun defineAction(result: FingerResponseEntity) {
        when (result.state) {
            FingerStateEnum.AUTH_SUCCESS -> {
                verificationPassed(true)
            }

            FingerStateEnum.AUTH_FAILED -> {
                CustomToast.cancelToast()
                requireContext().vibratePhone()
            } else -> {}
        }
    }

    companion object{
        const val CONFIRM_REQUEST_KEY = "confirm_request_key"
        const val EXTERNAL_REQUEST_KEY = "external_request_key"

        const val CONFIRM_EXTRA_KEY = "confirm_extra_key"
        const val CONFIRM_TYPE_EXTRA_KEY = "confirm_type_extra_key"
        const val CONFIRM_PASSWORD_ID_EXTRA_KEY = "confirm_password_id_extra_key"

        const val EXTERNAL_KEY_EXTRA = "external_key_extra"
    }

}