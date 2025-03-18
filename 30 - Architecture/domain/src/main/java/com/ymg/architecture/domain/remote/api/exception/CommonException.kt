package com.ymg.architecture.domain.remote.api.exception

sealed class CommonException : RuntimeException() {
    class NetworkException(
        override val message: String = "Network Error"
    ) : CommonException()

    class ServerException(
        override val message: String = "Server Error"
    ) : CommonException()

    class UnknownException(
        override val message: String = "Unknown Error"
    ) : CommonException()
}
