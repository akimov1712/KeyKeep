package ru.topbun.keyKeep.presentation.dialogs.setMasterPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.domain.useCases.security.CheckSetMasterPasswordUseCase
import ru.topbun.keyKeep.domain.useCases.security.SetMasterPasswordUseCase
import javax.inject.Inject

@HiltViewModel
class SetMasterPasswordViewModel @Inject constructor(
    private val setMasterPasswordUseCase: SetMasterPasswordUseCase
): ViewModel() {

    private val _stateSetPassword = MutableStateFlow(false)
    val stateSetPassword get() = _stateSetPassword.asStateFlow()

    fun setMasterPassword(password: String) = viewModelScope.launch {
        setMasterPasswordUseCase(password)
        _stateSetPassword.emit(true)
    }

}