package com.alio.ulio.view.ui.main


import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.alio.ulio.R
import com.alio.ulio.base.BaseAppCompatActivity
import com.alio.ulio.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : BaseAppCompatActivity<ActivityMainBinding,
        MainViewModel>(R.layout.activity_main) {
    private lateinit var navController: NavController

    override fun ActivityMainBinding.onCreate() {
        viewModel = MainViewModel(application)
        binding.viewmodel = viewModel

        /*val keyHash = Utility.getKeyHash(this@MainActivity)
        Log.d("Hash", keyHash)*/

        initViews()
    }

    private fun initViews() {
        navigationImagesMargin(binding.alarmBottomNavigation)
        binding.alarmBottomNavigation.setOnNavigationItemSelectedListener { it ->
            binding.alarmBottomNavigation.post {
                navigationImagesMargin(binding.alarmBottomNavigation)
            }
            true
        }

        navController = Navigation.findNavController(this, R.id.fragment_alarm)
        binding.alarmBottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navigationImagesMargin(binding.alarmBottomNavigation)
        }
    }

    private fun navigationImagesMargin(view : View) {
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                navigationImagesMargin(child)
            }
        } else if (view is ImageView) {
            val param = view.layoutParams as ViewGroup.MarginLayoutParams
            param.topMargin = convertDpToPx(14)
            view.layoutParams = param

        }
    }

    private fun convertDpToPx(dp : Int) : Int {
        return (dp * (resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}