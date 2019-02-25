package com.caetano.bruno.cartrawler.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.caetano.bruno.cartrawler.R
import com.caetano.bruno.cartrawler.presentation.model.RentalInfoViewModel
import com.caetano.bruno.cartrawler.presentation.model.RentalItemViewModel
import com.caetano.bruno.cartrawler.presentation.model.SortType
import com.caetano.bruno.cartrawler.presentation.list.RentalListContract
import kotlinx.android.synthetic.main.fragment_rental_list.progress
import kotlinx.android.synthetic.main.fragment_rental_list.rentalList
import kotlinx.android.synthetic.main.rental_header.pickUpDateTime
import kotlinx.android.synthetic.main.rental_header.pickUpLocation
import kotlinx.android.synthetic.main.rental_header.returnDateTime
import kotlinx.android.synthetic.main.rental_header.returnLocation
import org.koin.android.ext.android.inject


class RentalListFragment : Fragment(),
    RentalListContract.View {

    interface OnRentalListListener {
        fun onRentalSelected(rental: RentalItemViewModel)
    }

    private var listener: OnRentalListListener? = null

    private val presenter: RentalListContract.Presenter by inject()

    private var adapter = RentalListAdapter()

    private val errorDialog: AlertDialog by lazy { initErrorDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rental_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        presenter.init(this)
        presenter.loadRentals()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.rental_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sort_by_price_asc -> presenter.sortBy(SortType.PRICE_ASC)
            R.id.sort_by_price_desc -> presenter.sortBy(SortType.PRICE_DESC)
            R.id.sort_by_vendor_asc -> presenter.sortBy(SortType.VENDOR_AZ)
            R.id.sort_by_vendor_desc -> presenter.sortBy(SortType.VENDOR_ZA)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRecyclerView() {
        val recyclerView = rentalList
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.clickListener = { listener?.onRentalSelected(it) }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnRentalListListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showLoading() {
        progress.visibility = VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = GONE
    }

    override fun showRentalInfo(rentalInfoViewModel: RentalInfoViewModel) {
        pickUpDateTime?.text = rentalInfoViewModel.pickupTime
        pickUpLocation?.text = rentalInfoViewModel.pickupLocation
        returnDateTime?.text = rentalInfoViewModel.returnTime
        returnLocation?.text = rentalInfoViewModel.returnLocation
    }

    override fun showRentals(rentals: List<RentalItemViewModel>) {
        adapter.setRentals(rentals)
        adapter.notifyDataSetChanged()
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
            .setPositiveButton(R.string.retry) { _, _ ->
                presenter.loadRentals()
            }
            .create()
    }
}
