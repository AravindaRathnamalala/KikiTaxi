package com.araa.kiki_lib

import com.araa.kiki_lib.Constants.Companion.BASE_DELIVERY_COST
import com.araa.kiki_lib.Constants.Companion.MAX_CAPACITY
import com.araa.kiki_lib.Constants.Companion.MAX_SPEED
import com.araa.kiki_lib.model.ItemPackage
import com.araa.kiki_lib.model.Vehicle
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculationsTest {

    private var listOfPackages = mutableListOf(
        ItemPackage("PKG001", 50.0, 30.0, 125.0, 23.0, getDeliveryTime(30.0, MAX_SPEED)),
        ItemPackage("PKG002", 75.0, 125.0, 125.0, 23.0, getDeliveryTime(125.0, MAX_SPEED)),
        ItemPackage("PKG003", 175.0, 100.0, 125.0, 23.0, getDeliveryTime(100.0, MAX_SPEED)),
        ItemPackage("PKG004", 110.0, 60.0, 125.0, 23.0, getDeliveryTime(60.0, MAX_SPEED)),
        ItemPackage("PKG005", 155.0, 95.0, 125.0, 23.0, getDeliveryTime(95.0, MAX_SPEED))
    )

    private var vehicles = mutableListOf(Vehicle("VH001", 0.0), Vehicle("VH002", 0.0))


    @Test
    fun `calculate delivery cost`() {
        val deliveryCost1 = calculateDeliveryCost(BASE_DELIVERY_COST, 5.0, 5.0)
        val deliveryCost2 = calculateDeliveryCost(BASE_DELIVERY_COST, 15.0, 5.0)
        val deliveryCost3 = calculateDeliveryCost(BASE_DELIVERY_COST, 10.0, 100.0)
        assertEquals(175.0, deliveryCost1, 0.001)
        assertEquals(275.0, deliveryCost2, 0.001)
        assertEquals(700.0, deliveryCost3, 0.001)
    }

    @Test
    fun `get discounted price`() {
        val packageDiscount1 = getDiscountedPrice(175.0, "OFR001", 5.0, 5.0)
        val packageDiscount2 = getDiscountedPrice(700.0, "OFR003", 10.0, 100.0)
        val packageDiscount3 = getDiscountedPrice(700.0, "OFR008", 10.0, 100.0)
        val packageDiscount4 = getDiscountedPrice(700.0, "", 10.0, 100.0)
        assertEquals(0.0, packageDiscount1, 0.001)
        assertEquals(35.0, packageDiscount2, 0.001)
        assertEquals(0.0, packageDiscount3, 0.001)
        assertEquals(0.0, packageDiscount4, 0.001)
    }

    @Test
    fun `get delivery Time`() {
        val deliveryTime1 = getDeliveryTime(100.0, MAX_SPEED)
        assertEquals(1.42, deliveryTime1, 0.001)
    }

    @Test
    fun `find combinations`() {
        val combinations = findCombinations(listOfPackages, MAX_CAPACITY)
        assertEquals(4, combinations.size)
    }

    @Test
    fun `get package details with delivery time`() {

        val deliveryTimes = listOf(3.98, 1.78, 1.42, 0.85, 4.19)
        var packages = mutableListOf<ItemPackage>()
        val combinations = findCombinations(listOfPackages, MAX_CAPACITY)
        getPackageDetailsWithDeliveryTime(combinations, vehicles)
        combinations.forEach { itemPackages ->
            itemPackages.forEach {
                packages.add(it)
            }
        }
        assertEquals(5, packages.size)
        packages.forEach {
            assert(deliveryTimes.contains(it.deliveryTime))
        }
    }
}