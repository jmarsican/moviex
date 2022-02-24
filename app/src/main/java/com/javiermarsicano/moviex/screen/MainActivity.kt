package com.javiermarsicano.moviex.screen

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.javiermarsicano.moviex.R
import com.javiermarsicano.moviex.base.BaseActivity
import com.javiermarsicano.moviex.databinding.ActivityMainBinding
import com.javiermarsicano.moviex.screen.movies.MainScreenFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.movies_host_fragment) as NavHostFragment
        val appBarConfig = AppBarConfiguration(navHostFragment.navController.graph)

        binding.toolbar.inflateMenu(R.menu.items_menu)
        binding.toolbar.setupWithNavController(navHostFragment.navController, appBarConfig)
        binding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_settings -> {
                    Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_logout -> {
                    Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

}
