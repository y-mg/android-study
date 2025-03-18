package com.ymg.architecture.data.core.remote.interceptor

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

class LoggingInterceptor(
    private val json: Json
) : HttpLoggingInterceptor.Logger {
    companion object {
        private const val TAG_RETROFIT = "Retrofit"
    }

    override fun log(
        message: String
    ) = when {
        !message.isJsonObject() && !message.isJsonArray() -> {
            Timber.tag(TAG_RETROFIT).d("NETWORK INFO -> $message")
        }

        else -> try {
            val prettyMessage = json.encodeToString(Json.parseToJsonElement(message))
            Timber.tag(TAG_RETROFIT).d(prettyMessage)
        } catch (_: Exception) {
            Timber.tag(TAG_RETROFIT).d(message)
        }
    }

    private fun String?.isJsonObject(): Boolean = this?.startsWith("{") == true && this.endsWith("}")

    private fun String?.isJsonArray(): Boolean = this?.startsWith("[") == true && this.endsWith("]")
}
