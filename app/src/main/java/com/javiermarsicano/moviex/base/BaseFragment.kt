package com.javiermarsicano.moviex.base

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.javiermarsicano.moviex.R

abstract class BaseFragment:  Fragment() {
    fun showErrorMessage(msg: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.error)
            .setMessage(msg)
            .show()
    }
}