package com.caetano.bruno.cartrawler.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caetano.bruno.cartrawler.R
import com.caetano.bruno.cartrawler.presentation.model.RentalItemViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rental_item.view.baggageQuantity
import kotlinx.android.synthetic.main.rental_item.view.doorQuantity
import kotlinx.android.synthetic.main.rental_item.view.makeModel
import kotlinx.android.synthetic.main.rental_item.view.passengerQuantity
import kotlinx.android.synthetic.main.rental_item.view.priceCurrency
import kotlinx.android.synthetic.main.rental_item.view.rentalImage
import kotlinx.android.synthetic.main.rental_item.view.totalPrice
import kotlinx.android.synthetic.main.rental_item.view.vendorName

class RentalListAdapter : RecyclerView.Adapter<RentalListAdapter.ViewHolder>() {

    private var rentals: List<RentalItemViewModel> = emptyList()

    var clickListener: ((RentalItemViewModel) -> Unit)? = null

    fun setRentals(rentals: List<RentalItemViewModel>) {
        this.rentals = rentals
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rental_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rentals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rentals[position])
        holder.itemView.setOnClickListener {
            clickListener?.invoke(rentals[holder.adapterPosition])
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(rentalViewModel: RentalItemViewModel) {
            Picasso.get().load(rentalViewModel.pictureUrl).into(itemView.rentalImage)
            itemView.makeModel.text = rentalViewModel.makeModel
            itemView.doorQuantity.text = rentalViewModel.doorQuantity
            itemView.passengerQuantity.text = rentalViewModel.passengerQuantity
            itemView.baggageQuantity.text = rentalViewModel.baggageQuantity
            itemView.priceCurrency.text = rentalViewModel.currency
            itemView.totalPrice.text = rentalViewModel.totalPrice
            itemView.vendorName.text =
                itemView.resources.getString(R.string.by_vendor, rentalViewModel.vendor)
        }
    }

}