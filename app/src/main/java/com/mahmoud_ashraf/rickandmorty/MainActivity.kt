package com.mahmoud_ashraf.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mahmoudashraf.splash.SplashFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, SplashFragment()).commit()
    }
}