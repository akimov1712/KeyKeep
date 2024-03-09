package ru.topbun.keyKeep.presentation.screens.search

import ru.topbun.keyKeep.domain.enities.PasswordEntity

sealed class SearchState {

    data object Initial: SearchState()
    data object Loading: SearchState()
    data object EmptyList: SearchState()
    data class PasswordList(val list: List<PasswordEntity>): SearchState()

}