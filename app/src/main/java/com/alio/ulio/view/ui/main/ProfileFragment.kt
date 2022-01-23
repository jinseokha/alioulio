package com.alio.ulio.view.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alio.ulio.R
import com.alio.ulio.base.BaseFragment
import com.alio.ulio.databinding.ProfileFragmentBinding

class ProfileFragment : BaseFragment<ProfileFragmentBinding,
        ProfileViewModel>(R.layout.profile_fragment) {

    override fun ProfileFragmentBinding.onCreateView() {
        viewModel = ViewModelProvider(this@ProfileFragment).get(ProfileViewModel::class.java)
        binding.viewmodel = viewmodel
    }

}