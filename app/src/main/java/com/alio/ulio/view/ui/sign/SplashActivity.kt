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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.sdk.user.UserApiClient

class SplashActivity : BaseAppCompatActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash) {

    override fun ActivitySplashBinding.onCreate() {
        viewModel = SplashViewModel(application)
        binding.viewmodel = viewmodel

        viewModel.autoLoginCheck()

        Glide.with(this@SplashActivity).load(R.raw.splash_ani).into(binding.logo)
        observable()
        initFirebase()
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

    private fun initFirebase() {


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("test", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            /*val msg = getString(R.string.msg_token_fmt, token)
            Log.d("test", msg)*/
            //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
    }

    // https://eunoia3jy.tistory.com/134

    private fun updateResult(isNewIntent: Boolean = false) {
        //true -> notification 으로 갱신된 것
        //false -> 아이콘 클릭으로 앱이 실행된 것
       /* tvResult.text = (intent.getStringExtra("notificationType") ?: "앱 런처") + if (isNewIntent) {
            "(으)로 갱신했습니다."
        } else {
            "(으)로 실행했습니다."
        }*/
    }
}
