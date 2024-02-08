package com.araa.kiki_lib

import com.araa.kiki_lib.Constants.Companion.BASE_DELIVERY_COST
import com.araa.kiki_lib.Constants.Companion.MAX_CAPACITY
import com.araa.kiki_lib.Constants.Companion.MAX_SPEED
import com.araa.kiki_lib.model.ItemPackage
import com.araa.kiki_lib.model.Vehicle


fun main() {

    var numberOfPackages: Int
    var numberOfVehicles: Int
    var listOfPackages = mutableListOf<ItemPackage>()
    var vehicles = mutableListOf<Vehicle>()
    do {
        print("Enter the number of packages:")
        numberOfPackages = readlnOrNull()?.toIntOrNull() ?: -1
    } while (numberOfPackages <= 0)
    do {
        print("Enter number of available vehicles : ")
        numberOfVehicles = readlnOrNull()?.toIntOrNull() ?: -1
    } while (numberOfVehicles <= 0)

    for (i in 1..numberOfVehicles) {
        vehicles.add(Vehicle("VH$i", 0.0))
    }

//    create package list based on numberOfPackages
    for (i in 1..numberOfPackages) {
        var weight: Double
        do {
            print("Enter Weight: ")
            weight = readlnOrNull()?.toDoubleOrNull() ?: -1.0
        } while (weight <= 0)
        var distance: Double
        do {
            print("Enter Distance: ")
            distance = readlnOrNull()?.toDoubleOrNull() ?: -1.0
        } while (distance <= 0)
        print("Enter Coupon Code: ")
        val discountCode: String = readln()
        val deliveryFee: Double = calculateDeliveryCost(BASE_DELIVERY_COST, weight, distance)
        val packageID = "PKG$i"
        val discount = getDiscountedPrice(deliveryFee, discountCode, weight, distance)
        listOfPackages.add(
            ItemPackage(
                packageID,
                weight,
                distance,
                deliveryFee - discount,
                discount,
                getDeliveryTime(distance, MAX_SPEED)
            )
        )
    }

    listOfPackages.forEach {
        println("${it.packageId} ${it.discount} ${it.deliveryFee}")
    }

    getPackageDetailsWithDeliveryTime(findCombinations(listOfPackages, MAX_CAPACITY), vehicles)

    listOfPackages.forEach { println("${it.packageId} ${it.discount} ${it.deliveryFee} ${it.deliveryTime}") }
}
