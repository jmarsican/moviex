package com.javiermarsicano.moviex.views

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.javiermarsicano.moviex.R

class MainActivity : FragmentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add list fragment if this is first creation
    }

}
