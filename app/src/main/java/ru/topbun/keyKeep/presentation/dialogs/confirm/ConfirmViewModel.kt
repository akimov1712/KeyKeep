package ru.topbun.keyKeep.presentation.dialogs.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.domain.useCases.security.CheckCurrentMasterPasswordUseCase
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
    private val checkCurrentMasterPasswordUseCase: CheckCurrentMasterPasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow<ConfirmState>(ConfirmState.Initial)
    val state get() = _state.asStateFlow()

    fun checkCurrentPassword(password: String) = viewModelScope.launch {
        val result = checkCurrentMasterPasswordUseCase(password)
        _state.emit(ConfirmState.Result(result))
    }

}