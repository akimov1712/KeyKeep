package ru.topbun.keyKeep.presentation.dialogs.confirmDelete

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.domain.useCases.password.DeletePasswordUseCase
import javax.inject.Inject

@HiltViewModel
class ConfirmDeleteViewModel @Inject constructor(
    private val deletePasswordUseCase: DeletePasswordUseCase
): ViewModel() {

    private val _shouldCloseScreen = MutableSharedFlow<Unit>()
    val shouldCloseScreen = _shouldCloseScreen.asSharedFlow()

    fun deletePassword(id: Int) = viewModelScope.launch {
        deletePasswordUseCase(id)
        _shouldCloseScreen.emit(Unit)
    }

}