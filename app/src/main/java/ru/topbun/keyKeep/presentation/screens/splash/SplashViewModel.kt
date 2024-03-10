package ru.topbun.keyKeep.presentation.screens.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.topbun.keyKeep.domain.useCases.security.CheckSetMasterPasswordUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkSetMasterPasswordUseCase: CheckSetMasterPasswordUseCase
): ViewModel() {

    suspend fun checkSetMasterPassword() = checkSetMasterPasswordUseCase()

}