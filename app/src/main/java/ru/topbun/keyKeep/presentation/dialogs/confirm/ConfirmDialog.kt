package ru.topbun.keyKeep.presentation.dialogs.confirm

import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import dagger.hilt.android.AndroidEntryPoint
import ru.topbun.keyKeep.databinding.DialogConfirmBinding
import ru.topbun.keyKeep.presentation.base.BaseDialogFragment

@AndroidEntryPoint
class ConfirmDialog : BaseDialogFragment<DialogConfirmBinding>(DialogConfirmBinding::inflate){



    override fun setListenersInView() {
        super.setListenersInView()
        binding.pinEditText.setOtpCompletionListener {
            Log.d("TEST", it)
            val value = it == "4455"

            Log.d("TEST", value.toString())
            setFragmentResult(
                CONFIRM_REQUEST_KEY,
                bundleOf(CONFIRM_EXTRA_KEY to value)
            )
            dismiss()
        }
    }

    companion object{
        const val CONFIRM_REQUEST_KEY = "confirm_request_key"
        const val CONFIRM_EXTRA_KEY = "confirm_extra_key"
    }

}