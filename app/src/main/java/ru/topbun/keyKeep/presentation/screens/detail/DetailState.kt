package ru.topbun.keyKeep.presentation.screens.detail

import ru.topbun.keyKeep.domain.enities.PasswordEntity

sealed class DetailState {

    data object Initial: DetailState()
    data object DeleteItem: DetailState()
    data class PasswordItem(val item: PasswordEntity): DetailState()


}