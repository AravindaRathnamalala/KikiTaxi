package com.araa.kiki_lib

import com.araa.kiki_lib.Constants.Companion.DISTANCE_CONST
import com.araa.kiki_lib.Constants.Companion.WEIGHT_CONST
import com.araa.kiki_lib.model.Offer


fun calculateDeliveryCost(
    basePrice: Double, weight: Double, distance: Double
): Double {
    return basePrice + weight * WEIGHT_CONST + distance * DISTANCE_CONST
}

fun getDiscountedPrice(
    deliveryFee: Double,
    discountCode: String = "",
    weight: Double,
    distance: Double
): Double {
    if ((discountCode == Offer.OFR001.name)
        && (distance in (Offer.OFR001.minDistance..Offer.OFR001.maxDistance))
        && (weight in (Offer.OFR001.minWeight..Offer.OFR001.maxWeight))
    ) {
        return deliveryFee * Offer.OFR001.percentage * 0.01
    } else if ((discountCode == Offer.OFR002.name)
        && (distance in (Offer.OFR002.minDistance..Offer.OFR002.maxDistance))
        && (weight in (Offer.OFR002.minWeight..Offer.OFR002.maxWeight))
    ) {
        return deliveryFee * Offer.OFR002.percentage * 0.01
    } else if ((discountCode == Offer.OFR003.name)
        && (distance in (Offer.OFR003.minDistance..Offer.OFR003.maxDistance))
        && (weight in (Offer.OFR003.minWeight..Offer.OFR003.maxWeight))
    ) {
        return deliveryFee * Offer.OFR003.percentage * 0.01
    }
    return 0.0
}
