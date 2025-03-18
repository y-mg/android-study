package com.ymg.glide

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions

fun ImageView.bindImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun ImageView.bindImageWithOption(url: String) {
    Glide.with(this)
        .load(url)
        .override(100, 100) // 이미지 크기 조정
        .placeholder(android.R.drawable.menuitem_background) // 로드 전 대체 이미지
        .error(android.R.drawable.stat_notify_error) // 로드 실패 시 이미지
        .fallback(android.R.drawable.stat_notify_error) // URL 이 null 일 경우 대체 이미지
        .into(this)
}

val listener = object : RequestListener<Drawable> {
    override fun onResourceReady(
        resource: Drawable,
        model: Any,
        target: com.bumptech.glide.request.target.Target<Drawable>?,
        dataSource: DataSource,
        isFirstResource: Boolean
    ): Boolean {
        // 성공 시 동작
        return false
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: com.bumptech.glide.request.target.Target<Drawable>,
        isFirstResource: Boolean
    ): Boolean {
        // 실패 시 동작
        return false
    }
}

val option = RequestOptions()
    .override(100, 100)
    .placeholder(android.R.drawable.menuitem_background) // 로드 전 대체 이미지
    .error(android.R.drawable.stat_notify_error) // 로드 실패 시 이미지
    .fallback(android.R.drawable.stat_notify_error) // URL 이 null 일 경우 대체 이미지

fun ImageView.bindImageWithRequestOptions(url: String) {
    Glide.with(this)
        .load(url)
        .apply(option)
        .into(this)
}

fun ImageView.bindImageWithMemoryCache(url: String) {
    Glide.with(this)
        .load(url)
        .skipMemoryCache(true) // 메모리 캐싱을 비활성화하여 항상 새로운 이미지를 로드
        .into(this)
}

fun ImageView.bindImageWithDiskCacheStrategy(url: String) {
    // DiskCacheStrategy.ALL: 원본 이미지와 리사이즈된 이미지를 모두 캐싱한다.
    // DiskCacheStrategy.AUTOMATIC: 이미지 리소스를 분석하여 적절한 캐싱 전략을 자동으로 결정한다.
    // DiskCacheStrategy.DATA: 원본 이미지 파일만 캐싱한다.
    // DiskCacheStrategy.RESOURCE: 리사이즈된 이미지 파일만 캐싱한다.
    // DiskCacheStrategy.NONE: 디스크 캐싱을 사용하지 않는다.
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL) // 디스크 캐시 전략을 설정
        .into(this)
}

fun ImageView.bindImageWithOnlyRetrieveFromCache(url: String) {
    Glide.with(this)
        .load(url)
        .onlyRetrieveFromCache(true) // 캐시에서만 이미지를 로드하고, 캐시에 없을 경우 네트워크 요청을 하지 않음
        .into(this)
}