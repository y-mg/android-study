package com.ymg.mvp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ymg.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), UserView {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var presenter: UserPresenter

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

        presenter = UserPresenter(this, UserRepositoryImpl())
        presenter.loadUser()
    }

    @SuppressLint("SetTextI18n")
    override fun showUser(user: User) {
        binding.text.text = "이름: ${user.name}, 나이: ${user.age}"
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}