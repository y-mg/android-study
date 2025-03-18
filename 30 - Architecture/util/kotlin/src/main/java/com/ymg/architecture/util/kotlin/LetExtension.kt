package com.ymg.architecture.util.kotlin

inline fun <R> safeLet(
    vararg params: Any?,
    block: () -> R?
): R? {
    return if (params.all { it != null }) {
        block()
    } else {
        null
    }
}

inline fun <R> safeEmptyLet(
    vararg params: String?,
    block: (List<String>) -> R?
): R? {
    // 모든 파라미터가 null 이 아니고 비어있지 않은지 체크
    return if (params.all { it != null && it.trim().isNotEmpty() }) {
        // null 이 아니고 비어있지 않은 파라미터들을 블록에 전달
        block(params.filterNotNull())
    } else {
        null
    }
}

inline fun <R> safeEmptyLet(
    vararg lists: List<*>?,
    block: (List<List<*>>) -> R?
): R? {
    // 모든 리스트가 null 이 아니고 비어있지 않은지 체크
    return if (lists.all { it != null && it.isNotEmpty() }) {
        // null 이 아니고 비어있지 않은 리스트들을 블록에 전달
        block(lists.filterNotNull())
    } else {
        null
    }
}
