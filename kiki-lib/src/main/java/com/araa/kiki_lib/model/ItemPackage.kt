package com.araa.kiki_lib.model

data class ItemPackage(
    val packageId: String,
    val weight: Double = 0.0,
    val distance: Double = 0.0,
    val deliveryFee: Double,
    val discount: Double,
    var deliveryTime: Double
)
