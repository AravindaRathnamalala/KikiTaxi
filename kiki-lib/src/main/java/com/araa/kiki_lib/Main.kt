package com.araa.kiki_lib

import com.araa.kiki_lib.Constants.Companion.BASE_DELIVERY_COST
import com.araa.kiki_lib.Constants.Companion.MAX_CAPACITY
import com.araa.kiki_lib.Constants.Companion.MAX_SPEED
import com.araa.kiki_lib.model.ItemPackage
import com.araa.kiki_lib.model.Vehicle


fun main() {

    print("Enter number of packages: ")
    val numberOfPackages: Int = readlnOrNull()?.toInt() ?: 0
    print("Enter number of available vehicles : ")
    val numberOfVehicles: Int = readlnOrNull()?.toInt() ?: 0
    var vehicles = mutableListOf<Vehicle>()
    for (i in 1..numberOfVehicles) {
        vehicles.add(Vehicle("VH$i", 0.0))
    }

    var listOfPackages = mutableListOf<ItemPackage>()

    //create package list based on number of packages
//    for (i in 1..numberOfPackages) {
//        print("Enter Weight: ")
//        val weight: Double = readlnOrNull()?.toDouble() ?: 0.0
//        print("Enter Distance: ")
//        val distance: Double = readlnOrNull()?.toDouble() ?: 0.0
//        print("Enter Coupon Code: ")
//        val discountCode: String = readln()
//        val deliveryFee: Double = calculateDeliveryCost(BASE_DELIVERY_COST, weight, distance)
//        val packageID = "PKG$i"
//        val discount = getDiscountedPrice(deliveryFee, discountCode, weight, distance)
//        listOfPackages.add(
//            ItemPackage(
//                packageID,
//                weight,
//                distance,
//                deliveryFee - discount,
//                discount,
//                getDeliveryTime(distance, MAX_SPEED)
//            )
//        )
//    }


    listOfPackages.add(
        ItemPackage(
            "PKG001",
            50.0,
            30.0,
            125.0,
            23.0,
            getDeliveryTime(30.0, MAX_SPEED)
        )
    )
    listOfPackages.add(
        ItemPackage(
            "PKG002",
            75.0,
            125.0,
            125.0,
            23.0,
            getDeliveryTime(125.0, MAX_SPEED)
        )
    )
    listOfPackages.add(
        ItemPackage(
            "PKG003",
            175.0,
            100.0,
            125.0,
            23.0,
            getDeliveryTime(100.0, MAX_SPEED)
        )
    )
    listOfPackages.add(
        ItemPackage(
            "PKG004",
            110.0,
            60.0,
            125.0,
            23.0,
            getDeliveryTime(60.0, MAX_SPEED)
        )
    )
    listOfPackages.add(
        ItemPackage(
            "PKG005",
            155.0,
            95.0,
            125.0,
            23.0,
            getDeliveryTime(95.0, MAX_SPEED)
        )
    )

    listOfPackages.forEach {
        println("${it.packageId} ${it.discount} ${it.deliveryFee}")
    }

    getPackageDetailsWithDeliveryTime(findCombinations(listOfPackages, MAX_CAPACITY), vehicles)

    listOfPackages.forEach { println("${it.packageId} ${it.discount} ${it.deliveryFee} ${it.deliveryTime}") }
}
