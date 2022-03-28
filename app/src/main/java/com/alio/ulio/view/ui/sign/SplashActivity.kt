package com.alio.ulio.view.ui.sign

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivitySplashBinding
import com.alio.ulio.db.Preferences
import com.alio.ulio.view.ui.main.MainActivity
import com.bumptech.glide.Glide
import com.kakao.sdk.user.UserApiClient

class SplashActivity : BaseAppCompatActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash) {

    override fun ActivitySplashBinding.onCreate() {
        viewModel = SplashViewModel(application)
        binding.viewmodel = viewmodel

        viewModel.autoLoginCheck()

        Glide.with(this@SplashActivity).load(R.raw.splash_ani).into(binding.logo)
        observable()
    }

    private fun observable() {
        // 자동 로그인 체크
        viewModel.autoCheckLogin.observe(this, { success ->
            if (success) {
                // 자동로그인 성공

                UserApiClient.instance.me { user, error ->
                    Log.d("test", "닉네임 : ${user?.kakaoAccount?.profile?.nickname}")

                    Preferences.profile = user!!

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
            } else {
                // 자동로그인 실패
                //toast(getString(R.string.error_auto_login))

                val intent = Intent(this, SignActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        })
    }

    fun toast(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
