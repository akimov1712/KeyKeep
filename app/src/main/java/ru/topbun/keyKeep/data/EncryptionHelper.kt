package ru.topbun.keyKeep.data

import com.scottyab.aescrypt.AESCrypt


object EncryptionHelper {
    private const val password = "cmp3no23culr-#76FN*&N21"

    fun encrypt(str: String) = AESCrypt.encrypt(password, str)
    fun decrypt(encryptedStr: String) = AESCrypt.decrypt(password, encryptedStr)

}