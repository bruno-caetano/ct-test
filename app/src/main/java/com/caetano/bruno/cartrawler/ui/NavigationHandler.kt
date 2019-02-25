package com.caetano.bruno.cartrawler.ui

import android.support.v4.app.FragmentManager
import android.support.v7.app.ActionBar
import com.caetano.bruno.cartrawler.R
import com.caetano.bruno.cartrawler.ui.DetailsFragment
import com.caetano.bruno.cartrawler.ui.RentalListFragment

class NavigationHandler(
    private val fragmentManager: FragmentManager,
    private val actionBar: ActionBar
) {

    init {
        setupBackButton()
    }

    private fun setupBackButton() {
        refreshBackButton()
        addBackstackListener()
    }

    private fun refreshBackButton() {
        // Refresh back button if the screen was rotated
        if (fragmentManager.backStackEntryCount >= 1) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun addBackstackListener() {
        fragmentManager.addOnBackStackChangedListener {
            val showBackButton = fragmentManager.backStackEntryCount >= 1
            actionBar.setDisplayHomeAsUpEnabled(showBackButton)
        }
    }

    fun showListFragment() {
        fragmentManager.beginTransaction()
            .replace(R.id.container, RentalListFragment())
            .commit()
    }

    fun showDetailsFragment(vendorCode: String, vehicleCode: String) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .add(R.id.container, DetailsFragment.newInstance(vendorCode, vehicleCode))
            .addToBackStack(null)
            .commit()
    }
}