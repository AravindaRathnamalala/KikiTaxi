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
    for (i in 1..numberOfPackages) {
        print("Enter Weight: ")
        val weight: Double = readlnOrNull()?.toDouble() ?: 0.0
        print("Enter Distance: ")
        val distance: Double = readlnOrNull()?.toDouble() ?: 0.0
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

    val combinations = findCombinations(listOfPackages, MAX_CAPACITY)

    combinations.forEach {
        val max = it.maxByOrNull { it.distance }?.distance
        val availableVehicle = vehicles.minByOrNull { it.availability }
        it.forEach {
            if (availableVehicle != null) {
                it.deliveryTime += availableVehicle.availability
            }
        }
        vehicles.minByOrNull { it.availability }?.availability =
            vehicles.minByOrNull { it.availability }?.availability!! +
                    max?.let { it1 -> getDeliveryTime(it1, MAX_SPEED) }!! * 2

    }

    listOfPackages.forEach { println("${it.packageId} ${it.discount} ${it.deliveryFee} ${it.deliveryTime}") }
}
