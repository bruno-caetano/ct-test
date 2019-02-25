package com.caetano.bruno.data.source

import com.caetano.bruno.data.model.ServerResponse

interface VehicleService {
    fun loadVehicles(): ServerResponse
}