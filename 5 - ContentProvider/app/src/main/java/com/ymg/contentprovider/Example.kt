package com.ymg.contentprovider

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.util.Log

// 데이터 조회
fun exampleCheckContentProvider(context: Context) {
    val uri = Uri.parse("content://com.ymg.contentprovider.provider/my")
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        while (it.moveToNext()) {
            val columnIndex = it.getColumnIndex("column_name")
            val value = it.getString(columnIndex)
            Log.d("ContentProvider", "Value: $value")
        }
    }
}

// 데이터 삽입
fun exampleInsertContentProvider(context: Context) {
    val uri = Uri.parse("content://com.ymg.contentprovider.provider/my")
    val values = ContentValues().apply {
        put("column_name", "sample_value")
    }
    val newUri = context.contentResolver.insert(uri, values)
}

// 데이터 수정
fun exampleUpdateContentProvider(context: Context) {
    val uri = Uri.parse("content://com.ymg.contentprovider.provider/my")
    val values = ContentValues().apply {
        put("column_name", "updated_value")
    }
    val updatedRows = context.contentResolver.update(uri, values, "id = ?", arrayOf("1"))
}

// 데이터 삭제
fun exampleDeleteContentProvider(context: Context) {
    val uri = Uri.parse("content://com.ymg.contentprovider.provider/my")
    val deletedRows = context.contentResolver.delete(uri, "id = ?", arrayOf("1"))
}