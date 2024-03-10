package ru.topbun.keyKeep.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.domain.useCases.password.DeletePasswordUseCase
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val deletePasswordUseCase: DeletePasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow<DetailState>(DetailState.Initial)
    val state get() = _state.asStateFlow()

    fun deletePassword(id: Int) = viewModelScope.launch {
        deletePasswordUseCase(id)
        _state.emit(DetailState.DeleteItem)
    }

}