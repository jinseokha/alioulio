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
import com.kakao.sdk.talk.TalkApiClient
import com.kakao.sdk.user.UserApi
import com.kakao.sdk.user.UserApiClient

class ProfileFragment : BaseFragment<ProfileFragmentBinding,
        ProfileViewModel>(R.layout.profile_fragment) {

    override fun ProfileFragmentBinding.onCreateView() {
        viewModel = ViewModelProvider(this@ProfileFragment).get(ProfileViewModel::class.java)
        binding.viewmodel = viewmodel

        // 내 정보 보기
        UserApiClient.instance.me { user, error ->
            Log.d("test", "닉네임 : ${user?.kakaoAccount?.profile?.nickname}")
        }


        // 카카오톡 프로필 가져오기
        TalkApiClient.instance.profile { profile, error ->
            if (error != null) {
                Log.e("test", "카카오톡 프로필 가져오기 실패", error)
            } else if (profile != null) {
                Log.i("test", "카카오톡 프로필 가져오기 성공" +
                        "\n닉네임: ${profile.nickname}" +
                        "\n프로필사진: ${profile.thumbnailUrl}")
            }
        }

        // 카카오톡 친구 목록 가져오기 (기본)
        TalkApiClient.instance.friends { friends, error ->
            if (error != null) {
                Log.e("test", "카카오톡 친구 목록 가져오기 실패", error)
            }
            else if (friends != null) {
                Log.i("test", "카카오톡 친구 목록 가져오기 성공 \n${friends.elements?.joinToString("\n")}")

                // 친구의 UUID 로 메시지 보내기 가능
            }
        }

    }
}