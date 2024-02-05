package com.araa.kiki_lib


fun main() {
    print("Enter Base Price: ")
    val basePrice: Double = readlnOrNull()?.toDouble() ?: 0.0
    print("Enter Weight: ")
    val weight: Double = readlnOrNull()?.toDouble() ?: 0.0
    print("Enter Distance: ")
    val distance: Double = readlnOrNull()?.toDouble() ?: 0.0
    print("Enter Coupon Code: ")
    val discountCode: String = readln()

    print(calculateDeliveryCost(basePrice, weight, distance, discountCode ))
}