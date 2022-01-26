package com.alio.ulio.view.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.databinding.ProfileFragmentBinding
import com.alio.ulio.db.Preferences
import com.kakao.sdk.talk.TalkApiClient
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient

class ProfileFragment : BaseFragment<ProfileFragmentBinding,
        ProfileViewModel>(R.layout.profile_fragment) {

    var TAG = "test"

    override fun ProfileFragmentBinding.onCreateView() {
        viewModel = ViewModelProvider(this@ProfileFragment).get(ProfileViewModel::class.java)
        binding.viewmodel = viewmodel

/*        var profile : String? = Preferences.profile?.properties?.get("profile_image")

        Log.d("test", "Daata : ${profile}")*/


        //Log.d(TAG, "data : ${Preferences.profile.properties.}")
        // 카카오톡 친구 목록 가져오기 (기본)
       /* TalkApiClient.instance.friends { friends, error ->
            if (error != null) {
                Log.e("test", "카카오톡 친구 목록 가져오기 실패", error)
            }
            else if (friends != null) {
                Log.i("test", "카카오톡 친구 목록 가져오기 성공 \n${friends.elements?.joinToString("\n")}")

                // 친구의 UUID 로 메시지 보내기 가능
            }
        }

        binding.btnFab.setOnClickListener {
            // 연결끊기
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Log.e("test", "연결 끊기 실패", error)
                }
                else {
                    Log.i("test", "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                    requireActivity().finish()
                }
            }
        }*/

    }
}