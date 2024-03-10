package ru.topbun.keyKeep.presentation.dialogs.checkFinger

import ru.topbun.keyKeep.domain.enities.FingerResponseEntity

sealed class CheckFingerState {

    data object Initial: CheckFingerState()
    data class Result(val result: FingerResponseEntity): CheckFingerState()

}