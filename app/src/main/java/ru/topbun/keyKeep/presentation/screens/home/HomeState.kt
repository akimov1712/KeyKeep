package ru.topbun.keyKeep.presentation.screens.home

import ru.topbun.keyKeep.domain.enities.PasswordEntity

sealed class HomeState {

    data object Loading: HomeState()
    data object EmptyList: HomeState()
    data class PasswordList(val list: List<PasswordEntity>): HomeState()


}