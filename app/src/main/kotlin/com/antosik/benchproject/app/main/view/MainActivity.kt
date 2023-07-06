package com.antosik.benchproject.app.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.antosik.benchproject.R
import com.antosik.benchproject.app.common.bottomNavigationView.listener.BottomNavigationVisibilityListener
import com.antosik.benchproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            initBottomNavigation()
            setContentView(root)
        }
    }

    private fun ActivityMainBinding.initBottomNavigation() {
        val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
        setupWithNavController(activityMainBottomNavigation, navController)
        navController.addOnDestinationChangedListener(BottomNavigationVisibilityListener(activityMainBottomNavigation))
    }
}
