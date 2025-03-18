package com.ymg.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class MyContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        // ContentProvider 가 생성될 때 호출됨
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        // 데이터 조회 로직
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // 데이터 삽입 로직
        return null
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        // 데이터 수정 로직
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        // 데이터 삭제 로직
        return 0
    }

    override fun getType(uri: Uri): String? {
        // MIME 타입 반환
        return null
    }
}