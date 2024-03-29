package com.araa.kiki_lib

import com.araa.kiki_lib.Constants.Companion.DISTANCE_CONST
import com.araa.kiki_lib.Constants.Companion.WEIGHT_CONST
import com.araa.kiki_lib.model.ItemPackage
import com.araa.kiki_lib.model.Offer
import com.araa.kiki_lib.model.Vehicle
import java.text.DecimalFormat

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

fun getDeliveryTime(distance: Double, maxSpeed: Int): Double {
    return ((distance / maxSpeed) * 100).toInt() / 100.0
}

fun findCombinations(itemPackages: List<ItemPackage>, maxCapacity: Int): List<List<ItemPackage>> {
    val result = mutableListOf<List<ItemPackage>>()

    fun generateCombinations(
        index: Int,
        currentCombination: List<ItemPackage>,
        currentWeight: Double
    ) {
        if (currentWeight > maxCapacity) {
            return
        }
        if (index == itemPackages.size) {
            result.add(currentCombination.toList())
            return
        }
        // Include the weight at the current index
        generateCombinations(
            index + 1,
            currentCombination + itemPackages[index],
            currentWeight + itemPackages[index].weight
        )
        // Exclude the weight at the current index
        generateCombinations(index + 1, currentCombination, currentWeight)
    }
    generateCombinations(0, emptyList(), 0.0)

    return removeSubListsWithDuplicateValues(result.sortByDecendingOrder())
}

fun removeSubListsWithDuplicateValues(inputArray: List<List<ItemPackage>>): List<List<ItemPackage>> {
    val uniqueValues = mutableSetOf<ItemPackage>()
    val result = mutableListOf<List<ItemPackage>>()
    for (sublist in inputArray) {
        if (sublist.any { it in uniqueValues }) {
            continue
        }
        uniqueValues.addAll(sublist)
        result.add(sublist)
    }
    result.removeLast()
    return result
}

fun getPackageDetailsWithDeliveryTime(
    combinations: List<List<ItemPackage>>,
    listOfVehicles: List<Vehicle>
) {
    combinations.forEach { packages ->
        val max = packages.maxByOrNull { it.distance }?.distance
        val availableVehicle = listOfVehicles.minByOrNull { it.availability }
        packages.forEach {
            if (availableVehicle != null) {
                it.deliveryTime = (it.deliveryTime + availableVehicle.availability).roundToTwoDecimal()
            }
        }
        listOfVehicles.minByOrNull { it.availability }?.availability =
            listOfVehicles.minByOrNull { it.availability }?.availability!! +
                    max?.let { it1 -> getDeliveryTime(it1, Constants.MAX_SPEED) }!! * 2
    }
}









