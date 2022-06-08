package com.example.benchproject.app.main.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.benchproject.R
import com.example.benchproject.app.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO This is temporary before view binding it's only for show data on the device screen
        val mainTextView: TextView = findViewById(R.id.mainTextView)
        mainTextView.text = mainViewModel.getMovieData().toString()
    }
}
