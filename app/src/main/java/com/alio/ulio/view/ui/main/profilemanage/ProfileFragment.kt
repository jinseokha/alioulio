package com.alio.ulio.view.ui.main.profilemanage

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.binding.MyTransformation
import com.alio.ulio.databinding.ProfileFragmentBinding
import com.alio.ulio.db.Preferences
import com.alio.ulio.view.ui.main.profilemanage.adapter.FriendListAdapter
import com.alio.ulio.view.ui.main.profilemanage.personalinfo.PersonalInfoActivity
import com.alio.ulio.view.ui.sign.SignActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kakao.sdk.talk.TalkApiClient

class ProfileFragment : BaseFragment<ProfileFragmentBinding,
        ProfileViewModel>(R.layout.profile_fragment) {

    private lateinit var friendListAdapter : FriendListAdapter
    private lateinit var friendListManager : RecyclerView.LayoutManager

    override fun ProfileFragmentBinding.onCreateView() {
        viewModel = ViewModelProvider(this@ProfileFragment).get(ProfileViewModel::class.java)
        binding.viewmodel = viewmodel

        profileImageLoad()

        binding.btnFab.setOnClickListener {
            val intent = Intent(requireContext(), AlarmOptionActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            requireActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
        }

        binding.btnAgr.setOnClickListener {
            val intent = Intent(requireContext(), PersonalInfoActivity::class.java)
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            requireActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
        }

        //Log.d(TAG, "data : ${Preferences.profile.properties.}")
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

        initFriendList()
    }

    private fun profileImageLoad() {

        var profileImage : String? = Preferences.profile?.properties?.get("profile_image")

        Glide.with(requireActivity())
            .load(profileImage)
            .apply(RequestOptions().transform(MyTransformation(requireActivity(), 80, MyTransformation.CornerType.ALL)))
            .error(R.drawable.background_agree_round)
            .into(binding.imgProfile)

        binding.tvProfileName.text = Preferences.profile?.properties?.get("nickname")

        if (Preferences?.profile?.kakaoAccount?.email == null) {
            binding.tvEmail.visibility = View.GONE
        } else {
            binding.tvEmail.visibility = View.VISIBLE
            binding.tvEmail.text = Preferences?.profile?.kakaoAccount?.email
        }
    }

    private fun initFriendList() {
        friendListAdapter = FriendListAdapter(requireContext())
        friendListManager = LinearLayoutManager(requireContext())

        binding.recyclerView.layoutManager = friendListManager
        binding.recyclerView.adapter = friendListAdapter
    }
}