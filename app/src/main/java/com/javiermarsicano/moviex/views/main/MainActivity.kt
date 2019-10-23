package com.javiermarsicano.moviex.views.main

import android.os.Bundle
import com.javiermarsicano.moviex.R
import com.javiermarsicano.moviex.common.mvp.BaseMVPActivity
import com.javiermarsicano.moviex.data.models.MovieResult
import com.javiermarsicano.moviex.views.itemslist.ItemsFragment
import kotlinx.android.synthetic.main.activity_main.loaderView
import java.lang.ref.WeakReference

class MainActivity : BaseMVPActivity<MainView, MainPresenter>(), MainView {

    //it would be better to use dependency injection of presenter
    override fun getPresenter() = MainPresenterImpl(WeakReference(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = ItemsFragment.newInstance(0)

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, ItemsFragment.TAG).commit()
        }

        setLoaderView(loaderView)
    }

}
