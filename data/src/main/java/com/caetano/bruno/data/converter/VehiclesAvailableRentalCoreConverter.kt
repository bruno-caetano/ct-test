package com.caetano.bruno.data.converter

import com.caetano.bruno.data.model.AvailableVendorEntity
import com.caetano.bruno.data.model.PickUpLocationEntity
import com.caetano.bruno.data.model.ReturnLocationEntity
import com.caetano.bruno.data.model.ServerResponse
import com.caetano.bruno.data.model.TotalChargeEntity
import com.caetano.bruno.data.model.VehicleAvailableEntity
import com.caetano.bruno.data.model.VehicleEntity
import com.caetano.bruno.data.model.VehicleMakeModelEntity
import com.caetano.bruno.data.model.VehiclesAvailableRentalCoreEntity
import com.caetano.bruno.data.model.VendorEntity
import com.caetano.bruno.domain.model.AvailableVendor
import com.caetano.bruno.domain.model.PickUpLocation
import com.caetano.bruno.domain.model.RentalInfo
import com.caetano.bruno.domain.model.ReturnLocation
import com.caetano.bruno.domain.model.TotalCharge
import com.caetano.bruno.domain.model.Vehicle
import com.caetano.bruno.domain.model.VehicleAvailable
import com.caetano.bruno.domain.model.VehicleMakeModel
import com.caetano.bruno.domain.model.VehiclesAvailableRentalCore
import com.caetano.bruno.domain.model.Vendor
import java.math.BigDecimal

class VehiclesAvailableRentalCoreConverter {

    fun convertServerResponseToRentalCore(serverResponse: ServerResponse): VehiclesAvailableRentalCore {
        val serverEntity = serverResponse.vehiclesAvailableRentalCoreEntity
        return VehiclesAvailableRentalCore(
            getRentalInfo(serverEntity),
            getAvailableVendors(serverEntity)
        )
    }

    private fun getRentalInfo(serverEntity: VehiclesAvailableRentalCoreEntity): RentalInfo {
        val serverRentalInfo = serverEntity.rentalInfo
        return RentalInfo(
            convertToDate(serverRentalInfo.pickUpDateTime),
            convertToDate(serverRentalInfo.returnDateTime),
            getPickupLocation(serverRentalInfo.pickUpLocation),
            getReturnLocation(serverRentalInfo.returnLocation)
        )
    }

    private fun getPickupLocation(pickUpLocation: PickUpLocationEntity): PickUpLocation {
        return PickUpLocation(pickUpLocation.name)
    }

    private fun getReturnLocation(returnLocation: ReturnLocationEntity): ReturnLocation {
        return ReturnLocation(returnLocation.name)
    }

    private fun getAvailableVendors(serverEntity: VehiclesAvailableRentalCoreEntity): List<AvailableVendor> {
        return serverEntity.availableVendors.map { getAvailableVendor(it) }
    }

    private fun getAvailableVendor(availableVendor: AvailableVendorEntity): AvailableVendor {
        return AvailableVendor(
            availableVendor.vehiclesAvailable.map { getVehiclesAvailable(it) },
            getVendor(availableVendor.vendor)
        )
    }

    private fun getVehiclesAvailable(vehicleAvailable: VehicleAvailableEntity): VehicleAvailable {
        return VehicleAvailable(
            vehicleAvailable.status,
            getTotalCharge(vehicleAvailable.totalCharge),
            getVehicle(vehicleAvailable.vehicle)
        )
    }

    private fun getTotalCharge(totalCharge: TotalChargeEntity): TotalCharge {
        return TotalCharge(
            totalCharge.currencyCode,
            BigDecimal(totalCharge.estimatedTotalAmount),
            BigDecimal(totalCharge.rateTotalAmount)
        )
    }

    private fun getVehicle(vehicle: VehicleEntity): Vehicle {
        return Vehicle(
            vehicle.airConditionInd,
            vehicle.baggageQuantity,
            vehicle.code,
            vehicle.codeContext,
            vehicle.doorCount,
            vehicle.driveType,
            vehicle.fuelType,
            vehicle.passengerQuantity,
            vehicle.transmissionType,
            vehicle.pictureURL,
            getVehicleMakeModel(vehicle.vehicleMakeModel)
        )
    }

    private fun getVehicleMakeModel(vehicleMakeModel: VehicleMakeModelEntity): VehicleMakeModel {
        return VehicleMakeModel(vehicleMakeModel.name)
    }

    private fun getVendor(vendor: VendorEntity): Vendor {
        return Vendor(vendor.code, vendor.name)
    }

    fun convertVehicleEntityToVehicle(vehicle: VehicleAvailableEntity): VehicleAvailable {
        return getVehiclesAvailable(vehicle)
    }
}