package com.ymg.keystore

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec

class SecretHelper {
    private companion object {
        const val AES_KEY_ALIAS = "keystore_aes_key_alias"
        const val AES_ALGORITHM = "AES/GCM/NoPadding"
        const val KEYSTORE_PROVIDER = "AndroidKeyStore"
        const val KEY_SIZE_BIT = 256
        const val GCM_TAG_LENGTH_BIT = 128
    }

    // KeyStore 객체 초기화
    private val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER).apply {
        load(null)
    }

    init {
        // KeyStore 에 AES 키가 없으면 생성하고, 있으면 그대로 사용
        if (!keyStore.containsAlias(AES_KEY_ALIAS)) {
            generateAESKey()
        }
    }

    /**
     * AES 키 생성 및 KeyStore 에 저장
     */
    private fun generateAESKey() {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE_PROVIDER)
        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            AES_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).setKeySize(
            KEY_SIZE_BIT
        ).setBlockModes(
            KeyProperties.BLOCK_MODE_GCM
        ).setEncryptionPaddings(
            KeyProperties.ENCRYPTION_PADDING_NONE
        ).setUserAuthenticationRequired(
            false
        ).build()

        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()
    }

    /**
     * AES 로 데이터를 암호화하고 암호화된 데이터를 반환하는 함수
     * @param plainText - 암호화할 데이터 (평문)
     * @return String - 암호화된 데이터 (Base64 인코딩, IV 포함)
     */
    fun encrypt(
        plainText: String
    ): String {
        // KeyStore 에서 AES 비밀키 가져오기
        val secretKey = (keyStore.getEntry(AES_KEY_ALIAS, null) as KeyStore.SecretKeyEntry).secretKey

        val cipher = Cipher.getInstance(AES_ALGORITHM).apply {
            init(Cipher.ENCRYPT_MODE, secretKey)
        }

        // GCM 모드에서는 암호화 시에 IV(Initialization Vector)가 필요
        val iv = cipher.iv

        // 평문을 UTF-8 바이트 배열로 변환 후 암호화
        val encryptedBytes = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))

        // IV 와 암호화된 데이터를 합쳐서 Base64 인코딩하여 반환
        val combined = iv + encryptedBytes
        return Base64.encodeToString(combined, Base64.DEFAULT)
    }

    /**
     * AES 로 데이터를 복호화하고 원본 데이터를 반환하는 함수
     * @param base64EncryptedData 암호화된 데이터 (Base64 인코딩, IV 포함)
     * @return String 복호화된 평문
     */
    fun decrypt(
        base64EncryptedData: String
    ): String {
        // Base64 인코딩된 암호화된 데이터를 디코딩
        val decodedData = Base64.decode(base64EncryptedData, Base64.DEFAULT)

        // 첫 12바이트는 IV, 나머지는 암호화된 데이터
        val iv = decodedData.copyOfRange(0, 12)
        val encryptedBytes = decodedData.copyOfRange(12, decodedData.size)

        // KeyStore 에서 AES 비밀키 가져오기
        val secretKey = (keyStore.getEntry(AES_KEY_ALIAS, null) as KeyStore.SecretKeyEntry).secretKey

        val cipher = Cipher.getInstance(AES_ALGORITHM).apply {
            init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(GCM_TAG_LENGTH_BIT, iv))
        }

        // 암호화된 데이터를 복호화하여 평문으로 변환 후 반환
        return String(cipher.doFinal(encryptedBytes))
    }
}