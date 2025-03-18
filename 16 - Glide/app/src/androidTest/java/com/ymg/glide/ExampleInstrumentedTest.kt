package com.ymg.glide

import android.util.LruCache
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun `LRU_Cache`() {
        // LRU Cache 객체 생성(최대 5개의 항목을 저장할 수 있음)
        val cache = LruCache<String, String>(5)

        // 항목 추가
        cache.put("A", "Item A")
        cache.put("B", "Item B")
        cache.put("C", "Item C")
        cache.put("D", "Item D")
        cache.put("E", "Item E")
        println("Cache after adding 5 items: ${cache.snapshot().keys.toList()}") // [A, B, C, D, E]

        // 캐시 크기를 초과하는 항목 추가(LRU 정책에 따라 A가 제거됨)
        cache.put("F", "Item F")
        println("Cache after adding item F: ${cache.snapshot().keys.toList()}") // [B, C, D, E, F]

        // 가장 최근에 사용된 항목을 확인하고, "C" 항목을 최근에 사용한 것처럼 설정
        cache.get("C") // "C" 를 사용하면 "C" 가 가장 최근 항목이 된다.
        println("Cache after accessing C: ${cache.snapshot().keys.toList()}") // [B, D, E, F, C]

        // 항목 추가(LRU 정책에 따라 B가 제거됨)
        cache.put("G", "Item G")
        println("Cache after adding item G: ${cache.snapshot().keys.toList()}") // [D, E, F, C, G]

        // 캐시에서 특정 항목 제거
        cache.remove("E")
        println("Cache after removing item E: ${cache.snapshot().keys.toList()}") // [D, F, C, G]

        // 캐시가 비었는지 확인
        println("Cache contains item G: ${cache.get("G") != null}")
        println("Cache contains item B: ${cache.get("B") != null}")
    }
}