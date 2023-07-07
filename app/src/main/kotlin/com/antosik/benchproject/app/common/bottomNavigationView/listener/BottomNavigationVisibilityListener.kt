package com.antosik.benchproject.app.common.bottomNavigationView.listener

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.antosik.benchproject.R
import com.antosik.benchproject.app.common.bottomNavigationView.BenchBottomNavigationView

class BottomNavigationVisibilityListener(private val navigationView: BenchBottomNavigationView) :
    NavController.OnDestinationChangedListener {

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when (destination.id) {
            R.id.popularMoviesFragment -> navigationView.show()
            R.id.favouriteMovies -> navigationView.show()
            R.id.moreFragment -> navigationView.show()
            else -> navigationView.hide()
        }
    }
}
