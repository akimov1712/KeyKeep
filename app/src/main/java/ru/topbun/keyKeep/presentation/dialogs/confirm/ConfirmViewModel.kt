package ru.topbun.keyKeep.presentation.dialogs.confirm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.domain.useCases.security.CheckCurrentMasterPasswordUseCase
import ru.topbun.keyKeep.domain.useCases.security.CheckFingerScanUseCase
import ru.topbun.keyKeep.presentation.dialogs.checkFinger.CheckFingerState
import javax.inject.Inject

@HiltViewModel
class ConfirmViewModel @Inject constructor(
    private val checkCurrentMasterPasswordUseCase: CheckCurrentMasterPasswordUseCase,
    private val checkFingerScanUseCase: CheckFingerScanUseCase
): ViewModel() {

    private val _state = MutableSharedFlow<ConfirmState>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val state get() = _state.asSharedFlow()


    fun checkCurrentPassword(password: String) = viewModelScope.launch {
        val result = checkCurrentMasterPasswordUseCase(password)
        _state.emit(ConfirmState.MasterPasswordResult(result))
    }


    private fun checkFingerScan() = viewModelScope.launch {
        _state.emit(ConfirmState.Initial)
        checkFingerScanUseCase().collect {
            _state.emit(ConfirmState.FingerResult(it))
        }
    }

    init {
        checkFingerScan()
    }

}