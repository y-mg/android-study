package com.ymg.architecture.domain.local.usecase

import com.ymg.architecture.domain.repository.UserRepository
import javax.inject.Inject

// UseCase 는 주로 비즈니스 로직을 캡슐화하며, 클래스 네이밍은 동사 + 대상 + UseCase 형태를 권장한다.
// operator fun invoke() 를 사용하여 함수처럼 호출할 수 있다.
class SetAccessTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        accessToken: String
    ) = userRepository.setAccessToken(
        accessToken = accessToken
    )
}
