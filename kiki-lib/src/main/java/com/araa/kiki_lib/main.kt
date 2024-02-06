package com.araa.kiki_lib

import com.araa.kiki_lib.model.ItemPackage


fun main() {
    print("Enter Base Price: ")
    val basePrice: Double = readlnOrNull()?.toDouble() ?: 0.0

    print("Enter number of packages: ")
    val numberOfPackages: Int = readlnOrNull()?.toInt() ?: 0

    var listOfPackages = mutableListOf<ItemPackage>()

    for (i in 1..numberOfPackages) {
        print("Enter Weight: ")
        val weight: Double = readlnOrNull()?.toDouble() ?: 0.0
        print("Enter Distance: ")
        val distance: Double = readlnOrNull()?.toDouble() ?: 0.0
        print("Enter Coupon Code: ")
        val discountCode: String = readln()
        val deliveryFee: Double = calculateDeliveryCost(basePrice, weight, distance )
        val packageID = "PKG$i"
        val discount = getDiscountedPrice(deliveryFee, discountCode, weight, distance)

        listOfPackages.add(ItemPackage(packageID, weight, distance, deliveryFee - discount, discount))
    }

    listOfPackages.forEach{
        println("${it.packageId} ${it.weight} ${it.distance} ${it.deliveryFee} ${it.discount}")
    }
}