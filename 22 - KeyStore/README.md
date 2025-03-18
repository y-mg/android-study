![banner](./android.png)
# Keystore
- - -
KeyStore 는 안드로이드에서 암호화 키를 안전하게 저장하고 관리하는 시스템 제공 저장소이다.<br/>
이를 활용하면 암호화된 데이터를 보호하고, 키를 안전하게 보관하여 보안성을 높일 수 있다.<br/>
또한 KeyStore 를 사용하면 키가 단말기 외부로 유출되지 않도록 보호할 수 있으며, 하드웨어 보안 모듈(HSM)이나 Trusted Execution Environment(TEE) 를 통해 보안성이 강화된다.<br/>
<br/>
<br/>


## KeyStore 주요 기능
### 암호화 키 저장
RSA, AES, HMAC 등의 키를 안전하게 저장할 수 있다.<br/>
<br/>

### 키 접근 제어
특정 인증 방식(예: 생체 인증, PIN)과 함께 사용하여 키에 대한 접근을 제어할 수 있다.<br/>
<br/>

### 자동 삭제
일정 조건(예: 인증 실패, 디바이스 변경 등)에서 키가 자동 삭제되도록 설정할 수 있다.<br/>
<br/>

### 보안 하드웨어 연계
일부 기기에서는 보안 하드웨어(TEE, StrongBox)에서 키를 보호할 수 있다.<br/>
<br/>
<br/>

## KeyStore 동작 방식
## Key 생성
`KeyGenerator` 또는 `KeyPairGenerator` 를 사용하여 키를 생성하고 KeyStore 에 저장할 수 있다.<br/>
키 생성 시 `KeyGenParameterSpec` 을 활용하여 키의 속성을 정의할 수 있다.<br/>
예를 들어, 암호화 방식, 키 길이, 인증 요구 여부 등을 설정할 수 있다.<br/>
<br/>

## Key 사용
저장된 키를 가져와 암호화, 복호화, 서명 등의 작업에 사용할 수 있다.<br/>
예를 들어, `Cipher` 클래스를 활용하여 AES 암호화를 수행할 수 있다.<br/>
<br/>

## Key 삭제
더 이상 필요하지 않은 키는 `KeyStore.deleteEntry()` 를 사용하여 삭제할 수 있다.<br/>
또한, 특정 조건(예: 생체 인증 실패)에서 자동 삭제되도록 설정할 수도 있다.<br/>
<br/>
<br/>

## 예제 코드
```kotlin
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
```