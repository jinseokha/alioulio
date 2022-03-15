package com.alio.ulio.view.ui.main.profilemanage.personalinfo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityPersonalInfoBinding
import com.alio.ulio.view.ui.main.profilemanage.personalinfo.fragment.CondicionesFragment
import com.alio.ulio.view.ui.main.profilemanage.personalinfo.fragment.PersonalInfoFragment
import com.google.android.material.tabs.TabLayoutMediator

// 이용약관 및 개인정보 처리방침 화면
class PersonalInfoActivity : BaseAppCompatActivity<ActivityPersonalInfoBinding,
        PersonalInfoViewModel>(R.layout.activity_personal_info) {

    var tabTextList = arrayListOf("이용약관", "개인정보 처리방침")

    override fun ActivityPersonalInfoBinding.onCreate() {
        viewModel = PersonalInfoViewModel(application)

        binding.viewPager.adapter = SlidePagerAdapter(this@PersonalInfoActivity)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(binding.tabLayoutMenu, binding.viewPager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()

    }

    private inner class SlidePagerAdapter(fa : FragmentActivity) : FragmentStateAdapter(fa) {
        private val NUM_PAGES = 2

        override fun getItemCount(): Int
        = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> CondicionesFragment()
                else -> PersonalInfoFragment()
            }
        }
    }
}