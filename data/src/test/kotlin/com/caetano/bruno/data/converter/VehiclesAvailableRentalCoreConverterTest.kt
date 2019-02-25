package com.caetano.bruno.data.converter

import com.caetano.bruno.data.model.AvailableVendorEntity
import com.caetano.bruno.data.model.PickUpLocationEntity
import com.caetano.bruno.data.model.RentalInfoEntity
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
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import java.math.BigDecimal

@RunWith(BlockJUnit4ClassRunner::class)
class VehiclesAvailableRentalCoreConverterTest {

    val serverResponse = ServerResponse(
        VehiclesAvailableRentalCoreEntity(
            RentalInfoEntity(
                "2014-09-22T10:00:00Z",
                "2014-09-22T10:00:00Z",
                PickUpLocationEntity("PickUpLocation"),
                ReturnLocationEntity("ReturnLocation")
            ),
            listOf(
                AvailableVendorEntity(
                    listOf(
                        VehicleAvailableEntity(
                            "status",
                            TotalChargeEntity(
                                "Currency",
                                "10.00",
                                "20.00"
                            ),
                            VehicleEntity(
                                "airConditionInd",
                                "10",
                                "Code",
                                "CodeContext",
                                "4",
                                "driveType",
                                "fuelType",
                                "4",
                                "transmissionType",
                                "www.url.com.br",
                                VehicleMakeModelEntity("MakeModelName")
                            )
                        )
                    ), VendorEntity("VendorCode", "VendorName")
                )
            )
        )
    )

    val expected = VehiclesAvailableRentalCore(
        RentalInfo(
            convertToDate("2014-09-22T10:00:00Z"),
            convertToDate("2014-09-22T10:00:00Z"),
            PickUpLocation("PickUpLocation"),
            ReturnLocation("ReturnLocation")
        ),
        listOf(
            AvailableVendor(
                listOf(
                    VehicleAvailable(
                        "status",
                        TotalCharge(
                            "Currency",
                            BigDecimal("10.00"),
                            BigDecimal("20.00")
                        ),
                        Vehicle(
                            "airConditionInd",
                            "10",
                            "Code",
                            "CodeContext",
                            "4",
                            "driveType",
                            "fuelType",
                            "4",
                            "transmissionType",
                            "www.url.com.br",
                            VehicleMakeModel("MakeModelName")
                        )
                    )
                ), Vendor("VendorCode", "VendorName")
            )
        )
    )

    @Test
    fun `Convert server response to domain object`() {
        val converter = VehiclesAvailableRentalCoreConverter()
        val converted = converter.convertServerResponseToRentalCore(serverResponse)
        Assert.assertEquals(expected, converted)
    }

    @Test
    fun `Convert vehicle info to domain object`() {
        val converter = VehiclesAvailableRentalCoreConverter()
        val converted =
            converter.convertVehicleEntityToVehicle(
                serverResponse
                    .vehiclesAvailableRentalCoreEntity
                    .availableVendors.first()
                    .vehiclesAvailable.first()
            )
        Assert.assertEquals(expected.availableVendors.first().vehiclesAvailable.first(), converted)
    }

}