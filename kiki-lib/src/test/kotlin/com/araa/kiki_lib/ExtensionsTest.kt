package com.araa.kiki_lib

import com.araa.kiki_lib.model.ItemPackage
import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsTest {

    private val unSortedCombinations = listOf(
        listOf(
            ItemPackage(
                "PKG005",
                weight = 155.0,
                distance = 95.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 4.19
            )
        ),
        listOf<ItemPackage>(
            ItemPackage(
                "PKG002",
                weight = 75.0,
                distance = 125.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 1.78
            ),
            ItemPackage(
                "PKG004",
                weight = 110.0,
                distance = 60.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 0.85
            )
        ),
        listOf(
            ItemPackage(
                "PKG001",
                weight = 50.0,
                distance = 30.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 3.98
            )
        ),
        listOf(
            ItemPackage(
                "PKG003",
                weight = 175.0,
                distance = 100.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 1.42
            )
        )
    )

    private val sortedCombinations = listOf(
        listOf<ItemPackage>(
            ItemPackage(
                "PKG002",
                weight = 75.0,
                distance = 125.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 1.78
            ),
            ItemPackage(
                "PKG004",
                weight = 110.0,
                distance = 60.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 0.85
            )
        ),
        listOf(
            ItemPackage(
                "PKG003",
                weight = 175.0,
                distance = 100.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 1.42
            )
        ),
        listOf(
            ItemPackage(
                "PKG005",
                weight = 155.0,
                distance = 95.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 4.19
            )
        ),
        listOf(
            ItemPackage(
                "PKG001",
                weight = 50.0,
                distance = 30.0,
                deliveryFee = 125.0,
                discount = 23.0,
                deliveryTime = 3.98
            )
        )
    )

    @Test
    fun `round to two decimals`() {
        val number1 = 4.18999976
        val number2 = 4.1811
        val number3 = 0.0
        assertEquals(4.19, number1.roundToTwoDecimal(), 0.001)
        assertEquals(4.18, number2.roundToTwoDecimal(), 0.001)
        assertEquals(0.00, number3.roundToTwoDecimal(), 0.001)
    }

    @Test
    fun `sort by descending order` () {
        assertEquals(sortedCombinations, unSortedCombinations.sortByDecendingOrder())
    }
}