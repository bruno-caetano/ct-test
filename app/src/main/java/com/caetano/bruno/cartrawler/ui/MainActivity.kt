package com.caetano.bruno.cartrawler.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.caetano.bruno.cartrawler.R
import com.caetano.bruno.cartrawler.presentation.model.RentalItemViewModel

class MainActivity : AppCompatActivity(), RentalListFragment.OnRentalListListener {

    private lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationHandler = NavigationHandler(
            supportFragmentManager,
            supportActionBar!!
        )

        if (savedInstanceState == null)
            showListFragment()
    }

    private fun showListFragment() {
        navigationHandler.showListFragment()
    }

    private fun showDetailsFragment(vendorCode: String, vehicleCode: String) {
        navigationHandler.showDetailsFragment(vendorCode, vehicleCode)
    }

    override fun onRentalSelected(rental: RentalItemViewModel) {
        showDetailsFragment(rental.vendorCode, rental.vehicleCode)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
