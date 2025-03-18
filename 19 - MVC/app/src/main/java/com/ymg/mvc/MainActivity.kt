package com.ymg.mvc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ymg.mvc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var userView: UserView
    private lateinit var userController: UserController
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 모델과 뷰 생성
        userRepository = UserRepository()
        userView = UserView(binding.textView)

        // 컨트롤러 생성
        userController = UserController(userView, userRepository)

        // 버튼 클릭 시 사용자 정보 로드
        binding.button.setOnClickListener {
            userController.loadUser(1)  // 사용자 ID 1번의 정보를 로드
        }
    }
}