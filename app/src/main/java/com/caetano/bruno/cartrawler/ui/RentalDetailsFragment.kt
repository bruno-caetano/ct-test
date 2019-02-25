package com.caetano.bruno.cartrawler.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import com.caetano.bruno.cartrawler.R
import com.caetano.bruno.cartrawler.presentation.detail.RentalDetailsContract
import com.caetano.bruno.cartrawler.presentation.model.VehicleDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_rental_details.airConditioner
import kotlinx.android.synthetic.main.fragment_rental_details.baggageQuantity
import kotlinx.android.synthetic.main.fragment_rental_details.doorQuantity
import kotlinx.android.synthetic.main.fragment_rental_details.fuelType
import kotlinx.android.synthetic.main.fragment_rental_details.makeModel
import kotlinx.android.synthetic.main.fragment_rental_details.passengerQuantity
import kotlinx.android.synthetic.main.fragment_rental_details.priceCurrency
import kotlinx.android.synthetic.main.fragment_rental_details.rentalImage
import kotlinx.android.synthetic.main.fragment_rental_details.totalPrice
import kotlinx.android.synthetic.main.fragment_rental_details.transmissionType
import org.koin.android.ext.android.inject


private const val VENDOR_CODE = "vendorCode"
private const val VEHICLE_CODE = "vehicleCode"

class DetailsFragment : Fragment(),
    RentalDetailsContract.View {

    private lateinit var vendorCode: String
    private lateinit var vehicleCode: String

    private val presenter: RentalDetailsContract.Presenter by inject()

    private val errorDialog: AlertDialog by lazy { initErrorDialog() }

    companion object {
        @JvmStatic
        fun newInstance(vendorCode: String, vehicleCode: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(VENDOR_CODE, vendorCode)
                    putString(VEHICLE_CODE, vehicleCode)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            vendorCode = it.getString(VENDOR_CODE)!!
            vehicleCode = it.getString(VEHICLE_CODE)!!
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rental_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init(this)
        presenter.loadRental(vendorCode, vehicleCode)
    }

    override fun showError() {
        errorDialog.show()
    }

    override fun onStop() {
        errorDialog.dismiss()
        super.onStop()
    }

    private fun initErrorDialog(): AlertDialog {
        return AlertDialog.Builder(context!!)
            .setTitle(getString(R.string.network_error_title))
            .setMessage(getString(R.string.network_error_message))
            .setPositiveButton(R.string.network_error_back) { _, _ ->
                activity?.onBackPressed()
            }
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroy()
    }

    override fun showRental(vehicleViewModel: VehicleDetailViewModel) {
        Picasso.get().load(vehicleViewModel.pictureUrl).into(rentalImage)
        doorQuantity.text = vehicleViewModel.doorQuantity
        baggageQuantity.text = vehicleViewModel.baggageQuantity
        passengerQuantity.text = vehicleViewModel.passengerQuantity
        makeModel.text = vehicleViewModel.makeModel
        airConditioner.text =
            if (vehicleViewModel.airConditioner) getString(R.string.yes) else getString(R.string.no)
        transmissionType.text = vehicleViewModel.transmission
        fuelType.text = vehicleViewModel.fuelType
        totalPrice.text = vehicleViewModel.totalPrice
        priceCurrency.text = vehicleViewModel.currency
    }
}
