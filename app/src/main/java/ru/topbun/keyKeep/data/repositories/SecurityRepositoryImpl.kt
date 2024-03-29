package ru.topbun.keyKeep.data.repositories

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.topbun.keyKeep.R
import ru.topbun.keyKeep.domain.enities.FingerResponseEntity
import ru.topbun.keyKeep.domain.enities.FingerStateEnum
import ru.topbun.keyKeep.domain.repositories.SecurityRepository
import ru.topbun.keyKeep.data.DeviceCryptoObject
import javax.inject.Inject

class SecurityRepositoryImpl @Inject constructor(
    private val context: Application,
    private val deviceCrypto: DeviceCryptoObject,
    private val sharedPrefs: SharedPreferences
) : SecurityRepository {

    private val fingerStateFlow = MutableSharedFlow<FingerResponseEntity>(0, 10)


    private fun getMasterPassword(): String? {
        return sharedPrefs.getString(PREFS_KEY_MASTER_PASSWORD, null)
    }

    override suspend fun checkSetMasterPassword(): Boolean {
        val password = getMasterPassword()
        return password != null
    }

    override suspend fun checkCurrentMasterPassword(password: String): Boolean {
        if (!checkSetMasterPassword()) throw RuntimeException(context.getString(R.string.Password_not_defined))
        val passwordPrefs = getMasterPassword()
        return passwordPrefs == password
    }

    override suspend fun setMasterPassword(password: String) {
        sharedPrefs.edit().putString(PREFS_KEY_MASTER_PASSWORD, password).apply()
    }

    override suspend fun checkFingerPassword(): Flow<FingerResponseEntity> {
        startFingerprintAuthentication(context)
        return fingerStateFlow.asSharedFlow()
    }

    private fun startFingerprintAuthentication(context: Context) {
        val fingerprintManager = FingerprintManagerCompat.from(context)

        if (!fingerprintManager.isHardwareDetected) {
            fingerStateFlow.tryEmit(
                FingerResponseEntity(
                    FingerStateEnum.NOT_SUPPORT,
                    context.getString(R.string.The_device_does_not_support_a_fingerprint_scanner)
                )
            )
            return
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            fingerStateFlow.tryEmit(
                FingerResponseEntity(
                    FingerStateEnum.NO_REGISTERED,
                    context.getString(R.string.No_registered_fingerprints_on_the_device)
                )
            )
            return
        }

        val cryptoObject =
            FingerprintManagerCompat.CryptoObject(deviceCrypto.getCipher())
        fingerprintManager.authenticate(
            cryptoObject,
            0,
            null,
            object : FingerprintManagerCompat.AuthenticationCallback() {
                override fun onAuthenticationError(errMsgId: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errMsgId, errString)
                    fingerStateFlow.tryEmit(
                        FingerResponseEntity(
                            FingerStateEnum.AUTH_ERROR,
                            context.getString(R.string.error_check, errString)
                        )
                    )
                }

                override fun onAuthenticationSucceeded(result: FingerprintManagerCompat.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    fingerStateFlow.tryEmit(
                        FingerResponseEntity(
                            FingerStateEnum.AUTH_SUCCESS,
                            context.getString(R.string.device_have_finger_scan)
                        )
                    )
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    fingerStateFlow.tryEmit(
                        FingerResponseEntity(
                            FingerStateEnum.AUTH_FAILED,
                            context.getString(R.string.device_not_have_finger_scan)
                        )
                    )
                }
            },
            null
        )
    }

    companion object {
        private const val PREFS_KEY_MASTER_PASSWORD = "prefs_key_master_password"
    }

}