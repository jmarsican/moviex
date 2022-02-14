package com.javiermarsicano.moviex.views

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.javiermarsicano.moviex.R
import com.javiermarsicano.moviex.base.BaseActivity
import com.javiermarsicano.moviex.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainScreenFragment.newInstance())
            .commitNow()
    }

}
