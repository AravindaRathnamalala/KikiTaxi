package com.araa.kiki_lib.model

data class ItemPackage(
    val packageId: String,
    val weight: Double,
    val distance: Double,
    val deliveryFee: Double,
    val discount: Double
)
