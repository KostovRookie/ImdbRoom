package com.example.imdbroom.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imdbroom.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class ElderssScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        binding.imgSplash.alpha = 0.2f
        binding.imgSplash.animate().setDuration(2000).alpha(1f).withEndAction {

            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        setContentView(binding.root)
    }
}
