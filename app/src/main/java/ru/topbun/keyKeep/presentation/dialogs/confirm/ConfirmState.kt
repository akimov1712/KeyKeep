package ru.topbun.keyKeep.presentation.dialogs.confirm

import ru.topbun.keyKeep.domain.enities.FingerResponseEntity

sealed class ConfirmState {

    data object Initial: ConfirmState()
    data class Result(val result: Boolean): ConfirmState()

}