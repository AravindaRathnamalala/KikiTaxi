package com.araa.kiki_lib

import com.araa.kiki_lib.model.Offer

fun main() {
    val five = 5
    if (five in 1..10)
    print(calculateDeliveryCost(100.00, 10.0, 100.0, Offer.OFR003.name ))
}