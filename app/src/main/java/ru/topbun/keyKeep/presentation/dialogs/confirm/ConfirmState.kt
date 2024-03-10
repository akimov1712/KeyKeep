package ru.topbun.keyKeep.presentation.dialogs.confirm

import ru.topbun.keyKeep.domain.enities.FingerResponseEntity
import ru.topbun.keyKeep.presentation.dialogs.checkFinger.CheckFingerState

sealed class ConfirmState {

    data object Initial: ConfirmState()
    data class FingerResult(val result: FingerResponseEntity): ConfirmState()
    data class MasterPasswordResult(val result: Boolean): ConfirmState()

}