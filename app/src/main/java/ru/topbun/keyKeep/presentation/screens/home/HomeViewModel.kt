package ru.topbun.keyKeep.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.domain.useCases.password.GetPasswordListUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPasswordListUseCase: GetPasswordListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state get() = _state.asStateFlow()

    fun getPasswordList() = viewModelScope.launch {
        getPasswordListUseCase().collect{
            val state = if (it.isEmpty()) HomeState.EmptyList
            else HomeState.PasswordList(it)
            _state.emit(state)
        }
    }

    init {
        getPasswordList()
    }

}