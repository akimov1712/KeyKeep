package ru.topbun.keyKeep.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.topbun.keyKeep.domain.useCases.GetPasswordWithSearchRequestUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getPasswordWithSearchRequestUseCase: GetPasswordWithSearchRequestUseCase
): ViewModel() {

    private val _state = MutableStateFlow<SearchState>(SearchState.Initial)
    val state get() = _state.asStateFlow()

    fun getPassword(query: String = "") = viewModelScope.launch {
        _state.emit(SearchState.Loading)
        getPasswordWithSearchRequestUseCase(query).collect{
            val state = if (it.isEmpty()) SearchState.EmptyList
            else SearchState.PasswordList(it)
            _state.emit(state)
        }
    }

    init {
        getPassword()
    }

}