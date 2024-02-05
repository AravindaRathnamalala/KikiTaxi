package com.araa.kiki_lib.model

enum class Offer(
    val percentage: Int,
    val minDistance: Double,
    val maxDistance: Double,
    val minWeight: Double,
    val maxWeight: Double
) {
    OFR001(10, 0.0, 200.0, 70.0, 200.0),
    OFR002(7, 50.0, 150.0, 100.0, 250.0),
    OFR003(5, 50.0, 250.0, 10.0, 150.0);

    companion object {
        fun getOfferByCode(code: String): Offer? = values().find { it.name == code }
    }


}