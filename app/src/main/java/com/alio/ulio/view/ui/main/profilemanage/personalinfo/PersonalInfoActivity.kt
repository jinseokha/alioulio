package com.alio.ulio.view.ui.main.profilemanage.personalinfo

import android.graphics.Typeface
import android.graphics.fonts.Font
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.custom.calendar.utils.setTypeface
import com.alio.ulio.databinding.ActivityPersonalInfoBinding
import com.alio.ulio.view.ui.main.profilemanage.personalinfo.fragment.CondicionesFragment
import com.alio.ulio.view.ui.main.profilemanage.personalinfo.fragment.PersonalInfoFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Calendar.getInstance

// 이용약관 및 개인정보 처리방침 화면
class PersonalInfoActivity : BaseAppCompatActivity<ActivityPersonalInfoBinding,
        PersonalInfoViewModel>(R.layout.activity_personal_info) {

    var tabTextList = arrayListOf("이용약관", "개인정보 처리방침")

    override fun ActivityPersonalInfoBinding.onCreate() {
        viewModel = PersonalInfoViewModel(application)

        binding.backButton.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
        }

        binding.viewPager.adapter = PagerAdapter(supportFragmentManager)
        binding.viewPager.currentItem = 0

        binding.tabLayoutMenu.setupWithViewPager(binding.viewPager)
        binding.tabLayoutMenu.setSelectedTabIndicatorColor(ContextCompat.getColor(this@PersonalInfoActivity, R.color.black_454A57))


        binding.tabLayoutMenu.getTabAt(0)?.text = "이용약관"
        binding.tabLayoutMenu.getTabAt(1)?.text = "개인정보 처리방침"

        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayoutMenu))
        binding.tabLayoutMenu.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.select()

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right)
    }


    inner class PagerAdapter(supportFragmentManager: FragmentManager)
        : FragmentStatePagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment {

            return when (position) {
                0 ->
                    CondicionesFragment()
                else ->
                    PersonalInfoFragment()
            }
        }

        override fun getCount(): Int = 2
    }
}