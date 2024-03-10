package ru.topbun.keyKeep.presentation.dialogs.checkFinger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.domain.useCases.security.CheckFingerScanUseCase
import ru.topbun.keyKeep.domain.useCases.security.CheckFingerState
import javax.inject.Inject

@HiltViewModel
class CheckFingerViewModel @Inject constructor(
    private val checkFingerScanUseCase: CheckFingerScanUseCase
) : ViewModel() {

    private val _state = MutableSharedFlow<CheckFingerState>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val state get() = _state.asSharedFlow()

    private fun checkFingerScan() = viewModelScope.launch {
        _state.emit(CheckFingerState.Initial)
        checkFingerScanUseCase().collect {
            _state.emit(CheckFingerState.Result(it))
        }
    }

    init {
        checkFingerScan()
    }

}