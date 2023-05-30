package com.example.diplom

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.diplom.databinding.ActivityNavigationBinding
import com.example.diplom.ui.attendance.AttendanceFragment
import com.example.diplom.ui.login.LoginFragment
import com.example.diplom.ui.news.NewsFragment
import com.example.diplom.ui.schedule.ScheduleFragment

class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        onDestinationHideMenu()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news,
                R.id.navigation_schedule,
                R.id.navigation_attendance
            )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavView.setupWithNavController(navController)
    }

    fun setupBottomNavigationBarForTeacher() {
        val bottomBarMenu = binding.bottomNavView.menu
        bottomBarMenu.removeItem(R.id.navigation_schedule)
    }

    fun setupBottomNavigationBarForStudent() {
        val bottomBarMenu = binding.bottomNavView.menu
        bottomBarMenu.removeItem(R.id.navigation_attendance)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun onDestinationHideMenu() {
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentViewCreated(
                fm: FragmentManager,
                f: Fragment,
                v: View,
                savedInstanceState: Bundle?
            ) {
                when (f) {
                    is LoginFragment -> {
                        hideAppBar()
                        hideBottomNavigationMenu()
                    }

                    is NewsFragment,
                    is ScheduleFragment,
                    is AttendanceFragment -> {
                        hideAppBar()
                        showBottomNavigationMenu()
                    }
                }
            }
        }, true)
    }

    private fun showBottomNavigationMenu() {
        binding.bottomNavView.visibility = View.VISIBLE
    }

    private fun hideBottomNavigationMenu() {
        binding.bottomNavView.visibility = View.GONE
    }

    private fun hideAppBar() {
        supportActionBar?.hide()
    }

    private fun showAppBar() {
        supportActionBar?.show()
    }
}