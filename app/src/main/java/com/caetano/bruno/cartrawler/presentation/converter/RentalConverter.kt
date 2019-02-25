package com.caetano.bruno.cartrawler.presentation.converter

import com.caetano.bruno.cartrawler.presentation.model.RentalInfoViewModel
import com.caetano.bruno.cartrawler.presentation.model.RentalItemViewModel
import com.caetano.bruno.cartrawler.presentation.model.VehicleDetailViewModel
import com.caetano.bruno.cartrawler.util.DatePatterns
import com.caetano.bruno.cartrawler.util.format
import com.caetano.bruno.domain.model.VehicleAvailable
import com.caetano.bruno.domain.model.VehiclesAvailableRentalCore
import com.caetano.bruno.domain.model.Vendor

class RentalConverter {

    fun fromRentalCoreToRentalInfo(
        rentalCore: VehiclesAvailableRentalCore
    ): RentalInfoViewModel {
        return RentalInfoViewModel(
            rentalCore.rentalInfo.pickUpDateTime.format(DatePatterns.PICK_UP_RETURN_DATE_TIME.pattern),
            rentalCore.rentalInfo.returnDateTime.format(DatePatterns.PICK_UP_RETURN_DATE_TIME.pattern),
            rentalCore.rentalInfo.pickUpLocation.name,
            rentalCore.rentalInfo.returnLocation.name
        )
    }

    fun fromRentalCoreToRentalViewModel(
        vendor: Vendor,
        vehicleAvailable: VehicleAvailable
    ): RentalItemViewModel {
        return RentalItemViewModel(
            vehicleAvailable.vehicle.vehicleMakeModel.name.replace("or similar", ""),
            vehicleAvailable.vehicle.pictureURL,
            vehicleAvailable.vehicle.passengerQuantity,
            vehicleAvailable.vehicle.doorCount,
            vehicleAvailable.vehicle.baggageQuantity,
            vehicleAvailable.totalCharge.rateTotalAmount.toString(),
            vehicleAvailable.totalCharge.currencyCode,
            vendor.code,
            vehicleAvailable.vehicle.code,
            vendor.name
        )
    }

    fun fromVehicleToDetailViewModel(vehicleAvailable: VehicleAvailable): VehicleDetailViewModel {
        return VehicleDetailViewModel(
            vehicleAvailable.vehicle.vehicleMakeModel.name.replace("or similar", ""),
            vehicleAvailable.vehicle.pictureURL,
            vehicleAvailable.vehicle.passengerQuantity,
            vehicleAvailable.vehicle.doorCount,
            vehicleAvailable.vehicle.baggageQuantity,
            vehicleAvailable.totalCharge.rateTotalAmount.toString(),
            vehicleAvailable.totalCharge.currencyCode,
            vehicleAvailable.vehicle.airConditionInd.toBoolean(),
            vehicleAvailable.vehicle.fuelType,
            vehicleAvailable.vehicle.transmissionType
        )
    }
}