package com.alio.ulio.view.ui.sign

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityMainBinding
import com.alio.ulio.databinding.ActivitySignBinding
import com.alio.ulio.util.EventObserver
import com.alio.ulio.view.dialog.DialogAccessDeniedDialog
import com.alio.ulio.view.ui.main.MainActivity
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause

class SignActivity : BaseAppCompatActivity<ActivitySignBinding, SignViewModel>(R.layout.activity_sign) {

    private val requiredPermissions = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun ActivitySignBinding.onCreate() {
        viewModel = SignViewModel(application)
        binding.viewmodel = viewModel

        viewModel.autoLoginCheck()

        observable()
    }

    private fun observable() {
        viewModel.kakaoLoginClick.observe(this, EventObserver {
            var dialog = DialogAccessDeniedDialog()
            dialog.setDialogListener(object : DialogAccessDeniedDialog.DialogClickListener {
                override fun onAgreeClicked() {
                    // 동의
                    // TODO : 권한 체크 창 출력 필요
                    requestPermissions(requiredPermissions, REQUEST_RECORD_AUDIO_PERMISSION)
                }

                override fun onNotAgreeClick() {
                    // 비동의

                }
            })

            dialog.show(supportFragmentManager, "testDialog")
        })

        viewModel.memberInfoRule.observe(this, EventObserver {

        })


        // 자동 로그인 체크
        viewModel.autoCheckLogin.observe(this, { success ->
            if (success) {
                // 자동로그인 성공
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            } else {
                // 자동로그인 실패
                toast(getString(R.string.error_auto_login))
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //권한이 부여받은게 맞는지 check 권한부여받았으면 true 아니면 false
        val audioRequestPermissionGranted =
            requestCode == REQUEST_RECORD_AUDIO_PERMISSION &&
                    grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED

        if (audioRequestPermissionGranted) {
            // 권한 부여 완료
            if (LoginClient.instance.isKakaoTalkLoginAvailable(this)) {
                LoginClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                LoginClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        } else {
            // 권한 부여 X

        }
    }

    val callback : (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            when {
                error.toString() == AuthErrorCause.AccessDenied.toString() ->
                    toast(getString(R.string.access_denied))

                error.toString() == AuthErrorCause.InvalidClient.toString() ->
                    toast(getString(R.string.invalid_client))

                error.toString() == AuthErrorCause.InvalidGrant.toString() ->
                    toast(getString(R.string.invalid_grant))

                error.toString() == AuthErrorCause.InvalidRequest.toString() ->
                    toast(getString(R.string.invalid_request))

                error.toString() == AuthErrorCause.InvalidScope.toString() ->
                    toast(getString(R.string.invalid_scope))

                error.toString() == AuthErrorCause.Misconfigured.toString() ->
                    toast(getString(R.string.mis_configured))

                error.toString() == AuthErrorCause.ServerError.toString() ->
                    toast(getString(R.string.server_error))

                error.toString() == AuthErrorCause.Unauthorized.toString() ->
                    toast(getString(R.string.unauthorized))

                else ->
                    toast(getString(R.string.etc_error))
            }
        } else if (token != null) {
            toast(getString(R.string.success_login))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }
    }

    fun toast(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    companion object{
        //permission code 선언
        private const val REQUEST_RECORD_AUDIO_PERMISSION =201
    }
}