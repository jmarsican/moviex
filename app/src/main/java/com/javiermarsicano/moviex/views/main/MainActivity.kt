package com.javiermarsicano.moviex.views.main

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.javiermarsicano.moviex.R

class MainActivity : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add list fragment if this is first creation
    }

}
