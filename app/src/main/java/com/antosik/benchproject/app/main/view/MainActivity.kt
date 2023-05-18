package com.antosik.benchproject.app.main.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.antosik.benchproject.R
import com.antosik.benchproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // TODO Redundant new line
    // TODO Could be simplified
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).apply {
            initBottomNavigation()
            setContentView(root)
        }
    }

    // TODO Redundant new line
    // TODO Could be simplified
    private fun ActivityMainBinding.initBottomNavigation() {
        val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
        setupWithNavController(activityMainBottomNavigation, navController)
        // TODO Should be extracted
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.popularMoviesFragment -> activityMainBottomNavigation.show()
                R.id.favouriteMovies -> activityMainBottomNavigation.show()
                R.id.moreFragment -> activityMainBottomNavigation.show()
                else -> activityMainBottomNavigation.hide()
            }
        }
    }
}
