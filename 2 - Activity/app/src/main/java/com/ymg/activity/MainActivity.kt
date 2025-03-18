package com.ymg.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate 호출")

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 상태 복원 예시
        savedInstanceState?.getString("user_name")?.let {
            // 복원된 데이터 처리
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart 호출")
    }

    override fun onResume() {
        super.onResume()
        // 사용자와의 상호작용이 가능한 상태에서 필요한 작업 실행
        Log.d("MainActivity", "onResume 호출")
    }

    override fun onPause() {
        super.onPause()
        // 중요한 데이터 저장 작업 처리
        Log.d("MainActivity", "onPause 호출")
    }

    override fun onStop() {
        super.onStop()
        // 리소스 해제 또는 백그라운드 작업 처리
        Log.d("MainActivity", "onStop 호출")
    }

    override fun onDestroy() {
        super.onDestroy()
        // 리소스 해제 및 마지막 정리 작업
        Log.d("MainActivity", "onDestroy 호출")
    }
}