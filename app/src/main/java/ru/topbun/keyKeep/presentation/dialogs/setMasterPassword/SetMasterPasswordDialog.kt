package ru.topbun.keyKeep.presentation.dialogs.setMasterPassword

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.window.OnBackInvokedCallback
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.R
import ru.topbun.keyKeep.databinding.DialogSetMasterPasswordBinding
import ru.topbun.keyKeep.presentation.base.BaseDialogFragment
import ru.topbun.keyKeep.presentation.base.CustomToast
import ru.topbun.keyKeep.presentation.dialogs.confirm.ConfirmDialog
import ru.topbun.keyKeep.presentation.dialogs.confirm.ConfirmState

@AndroidEntryPoint
class SetMasterPasswordDialog :
    BaseDialogFragment<DialogSetMasterPasswordBinding>(DialogSetMasterPasswordBinding::inflate) {

    private val viewModel by viewModels<SetMasterPasswordViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.setCanceledOnTouchOutside(false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        lifecycleScope.launch {
            viewModel.stateSetPassword.collect{
                if (it){
                    findNavController().navigateUp()
                    putResultInFragmentResult(true)
                }
            }
        }
    }

    private fun putResultInFragmentResult(isSetPassword: Boolean) {
        setFragmentResult(
            SET_PASSWORD_REQUEST_KEY,
            bundleOf(SET_PASSWORD_EXTRA_KEY to isSetPassword)
        )
    }

    override fun setListenersInView() {
        super.setListenersInView()
        with(binding){
            pinEditText.addTextChangedListener(afterTextChanged = {
                changeEnabledButton(it?.length == 4)
            })
            btnSet.setOnClickListener {
                val password = pinEditText.text?.trim().toString()
                if (password.length == 4){
                    viewModel.setMasterPassword(password)
                }
            }
        }
    }

    private fun changeEnabledButton(isFieldFilled: Boolean) {
        binding.btnSet.isEnabled = isFieldFilled
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        putResultInFragmentResult(false)
    }

    companion object{
        const val SET_PASSWORD_REQUEST_KEY = "set_password_request_key"
        const val SET_PASSWORD_EXTRA_KEY = "set_password_extra_key"
    }
}