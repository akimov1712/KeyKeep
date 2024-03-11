package ru.topbun.keyKeep.presentation.screens.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.databinding.FragmentSplashBinding
import ru.topbun.keyKeep.presentation.base.BaseFragment
import ru.topbun.keyKeep.presentation.base.CustomToast
import ru.topbun.keyKeep.presentation.dialogs.confirm.ConfirmDialog
import ru.topbun.keyKeep.presentation.dialogs.setMasterPassword.SetMasterPasswordDialog

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getValueFromFragmentResult()
    }

    override fun setViews() {
        super.setViews()
        requestFingerPrint()
        checkSetPassword()
    }

    private fun checkSetPassword() {
        lifecycleScope.launch {
            val isSetPassword = viewModel.checkSetMasterPassword()
            if (isSetPassword) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSetMasterPasswordDialog())
            }
        }
    }

    private fun getValueFromFragmentResult(){
        setFragmentResultListener(SetMasterPasswordDialog.SET_PASSWORD_REQUEST_KEY) { _, bundle ->
            val value = bundle.getBoolean(SetMasterPasswordDialog.SET_PASSWORD_EXTRA_KEY, false)
            if (value){
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                CustomToast.toastDefault(requireContext(), "Чтобы пользоваться приложением, следует установить пароль")
                requireActivity().finish()
            }
        }
    }

    private fun requestFingerPrint() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.USE_FINGERPRINT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.USE_FINGERPRINT),
                REQUEST_CODE_PERMISSION
            )
        }
    }


    companion object {
        private const val REQUEST_CODE_PERMISSION = 1001
    }

}